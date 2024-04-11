package com.johnndev17.app_de_metas.data

import android.content.ContentValues
import android.content.Context
import com.johnndev17.app_de_metas.model.GoalsConstants
import com.johnndev17.app_de_metas.model.GoalsModel

class GoalsRepository private constructor(context: Context) {

    private var goalsDataBase = GoalsDataBase(context)

    companion object {

        private lateinit var repository: GoalsRepository

        fun getInstance(context: Context): GoalsRepository {

            if(!Companion::repository.isInitialized){

                repository = GoalsRepository(context)
            }

            return repository
        }
    }

    fun insert(goals: GoalsModel): Boolean{

        return try{
            val db = goalsDataBase.writableDatabase
            val progress = if(goals.progress) 1 else 0

            val values = ContentValues()
            values.put(GoalsConstants.KEY.COLUMNS.NAME, goals.goalsName)
            values.put(GoalsConstants.KEY.COLUMNS.PROGRESS, progress)

            db.insert(GoalsConstants.KEY.TABLE_NAME, null, values)

            true
        } catch (e: Exception){

            false
        }
    }

    fun update(goals: GoalsModel): Boolean{

       return try {
            val db = goalsDataBase.writableDatabase
            val progress = if(goals.progress) 1 else 0

            val values = ContentValues()
            values.put(GoalsConstants.KEY.COLUMNS.NAME, goals.goalsName)
            values.put(GoalsConstants.KEY.COLUMNS.PROGRESS, progress)

            val selection = GoalsConstants.KEY.COLUMNS.ID + " = ? "
            val args = arrayOf(goals.id.toString())

            db.update(GoalsConstants.KEY.TABLE_NAME, values, selection, args)

            true

        }catch (e: Exception){

            false
        }
    }

    fun remove(id: Int): Boolean{

        return try{

            val db = goalsDataBase.writableDatabase

            val selection = GoalsConstants.KEY.COLUMNS.ID + " = ? "
            val args = arrayOf(id.toString())

            db.delete(GoalsConstants.KEY.TABLE_NAME, selection, args)

            true
        } catch (e: Exception){

            false
        }
    }

    fun allGoals(): List<GoalsModel>{

        var list = mutableListOf<GoalsModel>()

        try {

            val db = goalsDataBase.readableDatabase

            val projection = arrayOf(
                GoalsConstants.KEY.COLUMNS.ID,
                GoalsConstants.KEY.COLUMNS.NAME,
                GoalsConstants.KEY.COLUMNS.PROGRESS
            )

            val cursor = db.query(GoalsConstants.KEY.TABLE_NAME, projection, null, null,
                null, null, null, )

            if(cursor != null && cursor.count > 0){

                while(cursor.moveToNext()){

                    val id = cursor.getInt(cursor.getColumnIndex(GoalsConstants.KEY.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(GoalsConstants.KEY.COLUMNS.NAME))
                    val progress = cursor.getInt(cursor.getColumnIndex(GoalsConstants.KEY.COLUMNS.PROGRESS))

                    list.add(GoalsModel(id, name, progress == 1))
                }
            }

            cursor.close()

        }catch (e: Exception){

            return list
        }

        return list
    }

    fun inicialGoals(): List<GoalsModel>{

        var list = mutableListOf<GoalsModel>()

        try {

            val db = goalsDataBase.readableDatabase

            val projection = arrayOf(
                GoalsConstants.KEY.COLUMNS.ID,
                GoalsConstants.KEY.COLUMNS.NAME,
                GoalsConstants.KEY.COLUMNS.PROGRESS
            )

            val selection = GoalsConstants.KEY.COLUMNS.PROGRESS + " = ?"
            val args = arrayOf("1")

            val cursor = db.query(GoalsConstants.KEY.TABLE_NAME, projection, selection, args,
                null, null, null)

            if(cursor != null && cursor.count > 0){

                while(cursor.moveToNext()){

                    val id = cursor.getInt(cursor.getColumnIndex(GoalsConstants.KEY.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(GoalsConstants.KEY.COLUMNS.NAME))
                    val progress = cursor.getInt(cursor.getColumnIndex(GoalsConstants.KEY.COLUMNS.PROGRESS))

                    list.add(GoalsModel(id, name, progress == 1))
                }
            }

            cursor.close()

        } catch (e: Exception){

            return list
        }

        return list
    }

    fun inProgressGoals(): List<GoalsModel>{

        var list = mutableListOf<GoalsModel>()

        try {

            val db = goalsDataBase.readableDatabase

            val projection = arrayOf(
                GoalsConstants.KEY.COLUMNS.ID,
                GoalsConstants.KEY.COLUMNS.NAME,
                GoalsConstants.KEY.COLUMNS.PROGRESS
            )

            val selection = GoalsConstants.KEY.COLUMNS.PROGRESS + " = ?"
            val args = arrayOf("0")

            val cursor = db.query(GoalsConstants.KEY.TABLE_NAME, projection, selection, args,
                null, null, null)

            if(cursor != null && cursor.count > 0){

                while(cursor.moveToNext()){

                    val id = cursor.getInt(cursor.getColumnIndex(GoalsConstants.KEY.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(GoalsConstants.KEY.COLUMNS.NAME))
                    val progress = cursor.getInt(cursor.getColumnIndex(GoalsConstants.KEY.COLUMNS.PROGRESS))

                    list.add(GoalsModel(id, name, progress == 1))
                }
            }

            cursor.close()

        } catch (e: Exception){

            return list
        }

        return list
    }

    fun getGoal(id: Int): GoalsModel?{

        var goal: GoalsModel? = null

        try {

            val db = goalsDataBase.readableDatabase

            val projection = arrayOf(
                GoalsConstants.KEY.COLUMNS.ID,
                GoalsConstants.KEY.COLUMNS.NAME,
                GoalsConstants.KEY.COLUMNS.PROGRESS
            )

            val selection = GoalsConstants.KEY.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(GoalsConstants.KEY.TABLE_NAME, projection, selection, args,
                null, null, null)

            if(cursor != null && cursor.count > 0){

                while(cursor.moveToNext()){

                    val name = cursor.getString(cursor.getColumnIndex(GoalsConstants.KEY.COLUMNS.NAME))
                    val progress = cursor.getInt(cursor.getColumnIndex(GoalsConstants.KEY.COLUMNS.PROGRESS))

                    goal = GoalsModel(id, name, progress == 1)
                }
            }

            cursor.close()

        } catch (e: Exception){

            return goal
        }

        return goal
    }
}