package gov.census.cspro.csentrylauncher

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CSProCase(
        @PrimaryKey var key : String,
        var numericVal : Double,
        var stringVal : String )