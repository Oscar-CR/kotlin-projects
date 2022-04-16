package org.bedu.login.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Insert
    fun insertAll(user: List<User>)

   @Update
   suspend fun updateUser(user: User)

   @Delete
   suspend fun removeUser(user: User)


    @Query("SELECT * FROM User")
    fun getUsers():LiveData<List<User>>
   /*

   @Query("DELETE FROM User WHERE id=:id")
   fun removeUserById(id: Int)



   @Query("SELECT * FROM User WHERE id=:id")
   fun getUserById(id: Int)

   @Query("SELECT * FROM User WHERE nombre=:nombre")
   fun getUserByName(nombre:String): User

    */



}