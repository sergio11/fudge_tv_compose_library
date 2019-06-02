package sanchez.sanchez.sergio.brownie_db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Support DAO Interface
 */
interface ISupportDAO<T> {

    /**
     * Insert
     * @param entity
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: T): LongArray

    /**
     * Insert
     * @param entity
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: T): Long


    /**
     * Update
     * @param entity
     */
    @Update
    fun update(entity: T)

    /**
     * Delete
     * @param entity
     */
    @Delete
    fun delete(entity: T)

    /**
     * Find All
     */
    fun findAll(): List<T>

    /**
     * Count
     */
    fun count(): Int
}