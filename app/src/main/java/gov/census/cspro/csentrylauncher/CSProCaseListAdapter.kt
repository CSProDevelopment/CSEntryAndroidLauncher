package gov.census.cspro.csentrylauncher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CSProCaseListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<CSProCaseListAdapter.CaseViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var cases = emptyList<CSProCase>()

    inner class CaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numericValView: TextView = itemView.findViewById(R.id.numeric_val)
        val stringValView: TextView = itemView.findViewById(R.id.string_val)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaseViewHolder {
        val itemView = inflater.inflate(R.layout.case_item, parent, false)
        return CaseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CaseViewHolder, position: Int) {
        val current = cases[position]
        holder.numericValView.text = current.numericVal.toString()
        holder.stringValView.text = current.stringVal
    }

    internal fun setCases(cases: List<CSProCase>) {
        this.cases = cases
        notifyDataSetChanged()
    }

    override fun getItemCount() = cases.size
}