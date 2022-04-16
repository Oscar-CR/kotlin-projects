package org.bedu.login.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
data class User constructor(
    @PrimaryKey(autoGenerate = true)val id:Int = 0,
    @ColumnInfo val nombre:String?,
    @ColumnInfo val correo:String?,
    @ColumnInfo val password:String?,
    @ColumnInfo val telefono: String?
        )