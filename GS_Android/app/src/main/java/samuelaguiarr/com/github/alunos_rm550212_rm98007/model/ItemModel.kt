package samuelaguiarr.com.github.alunos_rm550212_rm98007.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val local: String,
    val evento: String,
    val impacto: String,
    val data: String,
    val afetadas: String
)
