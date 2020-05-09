package br.com.components.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.components.model.Botao
import br.com.components.R
import br.com.components.database.Database
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_list.view.*
import java.lang.ClassCastException

class MainFragment : Fragment() {

    private lateinit var listenet: OnItemListSelected
    private lateinit var botoes: MutableList<Botao>

    companion object{
        const val GRID_COLUMNS = 3
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnItemListSelected){
            listenet = context
        } else {
            throw ClassCastException("$context must implemented")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated( savedInstanceState )
        initItems()
    }

    private fun initItems(){
        botoes = Database.getBotoes() as MutableList<Botao>
        rv_botoes.setHasFixedSize(false)
        rv_botoes.layoutManager = GridLayoutManager(activity, GRID_COLUMNS, RecyclerView.VERTICAL, false)
        rv_botoes.adapter = BotaoListAdapter()
    }

    internal inner class BotaoListAdapter() : RecyclerView.Adapter<ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent,false))

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var botao = botoes[position]
            holder.bind(botao)
            holder.itemView.setOnClickListener{
                listenet.onSelected(botao)
            }
        }
        override fun getItemCount() = botoes.size
    }

    internal inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val bt = itemView.botao

        fun bind (botao: Botao){
            bt.id = botao.id
            bt.text = (botao.descricao)
        }
    }

    interface OnItemListSelected {
        fun onSelected(botao: Botao)
    }
}