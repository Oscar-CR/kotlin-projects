package org.bedu.camera

import android.os.Bundle
import android.util.Size
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import org.bedu.camera.databinding.ActivityCameraBinding
import java.io.File
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding
    private val executor = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startCamera()
    }

    private fun startCamera(){
        val previewConfig = PreviewConfig.Builder().apply {
            setTargetResolution(Size(640, 480))
        }.build()

        val preview = Preview(previewConfig)

        preview.setOnPreviewOutputUpdateListener {
            val parent =  binding.parent as ViewGroup
            parent.removeView(binding.cameraPreview)
            parent.addView(binding.cameraPreview, 0)

            binding.cameraPreview.setSurfaceTexture(it.surfaceTexture)

        }

        val imageCapture = captureConfig()

        //Agregamos la función de Preview y de Captura de imagen al ciclo de vida de la cámara
        CameraX.bindToLifecycle(this, preview,imageCapture)
    }

    fun captureConfig(): ImageCapture {
        // Creando el objeto para crear el caso de uso de captura de imagen
        val imageCaptureConfig = ImageCaptureConfig.Builder()
            .apply {
                setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
            }.build()

        // Creamos nuestro objeto de captura con su configuración
        val imageCapture = ImageCapture(imageCaptureConfig)


        //Capturar y guardar imagen cuando se de click al botón de captura
        binding.captureButton.setOnClickListener {
            //Creando el archivo en la ruta
            val file = File(externalMediaDirs.first(),
                "${System.currentTimeMillis()}.jpg")

            //Tomamos la afoto y asignamos el listener
            imageCapture.takePicture(file, executor,
                object : ImageCapture.OnImageSavedListener {

                    //Enviamos el error a log y a un Toast
                    override fun onError(
                        imageCaptureError: ImageCapture.ImageCaptureError,
                        message: String,
                        exc: Throwable?
                    ) {
                        val msg = "Photo capture failed: $message"
                        Log.e("CameraXApp", msg, exc)
                        binding.cameraPreview.post {
                            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                        }
                    }

                    //Mostramos un Toast y un log de imagen guardada
                    override fun onImageSaved(file: File) {
                        val msg = "¡Imagen guardada!: ${file.absolutePath}"
                        Log.d("Camera", msg)
                        binding.cameraPreview.post {
                            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }

        return imageCapture
    }
}