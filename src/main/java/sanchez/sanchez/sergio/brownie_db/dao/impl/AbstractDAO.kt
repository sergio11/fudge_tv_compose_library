package sanchez.sanchez.sergio.brownie_db.dao.impl

import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import sanchez.sanchez.sergio.brownie_db.dao.ISupportDAO
import java.lang.reflect.ParameterizedType

/**
    Abstract DAO Support Class
 **/
abstract class AbstractDAO<T> : ISupportDAO<T> {

    /**
     * Find All
     */
    override fun findAll(): List<T> {
        val query = SimpleSQLiteQuery(
            "select * from ${getTableName()}"
        )
        return findAll(query)
    }

    /**
     * Count
     */
    override fun count(): Int {
        val query = SimpleSQLiteQuery(
            "select count(*) from ${getTableName()}"
        )
        return count(query)
    }

    /**
     *
     * Private Methods
     *
     */


    /**
     * Find All
     * @param query
     */
    @RawQuery
    protected abstract fun findAll(query: SupportSQLiteQuery): List<T>

    /**
     * Count
     * @param query
     */
    @RawQuery
    protected abstract fun count(query: SupportSQLiteQuery): Int

    /**
     * Get Table Name
     */
    private fun getTableName(): String {
        val clazz = (javaClass.superclass!!.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<*>
        return clazz.simpleName
    }

}