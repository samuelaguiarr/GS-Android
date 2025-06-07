package samuelaguiarr.com.github.alunos_rm550212_rm98007.data

import androidx.room.Database
import androidx.room.RoomDatabase
import samuelaguiarr.com.github.alunos_rm550212_rm98007.model.ItemModel

@Database(entities = [ItemModel::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}
