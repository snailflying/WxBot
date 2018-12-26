package io.merculet.core.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * @author Aaron
 * @email aaron@magicwindow.cn
 * @date 22/12/2017 2:59 PM
 * @description
 */
abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    private var checkBoxVisibility = false

    protected var list: MutableList<T> = mutableListOf()

    lateinit var context: Context

    fun getData(): MutableList<T> = list

    //增加一列数据
    fun addData(data: List<T>?) {
        if (data == null || data.isEmpty()) {
            return
        }
        val positionStart = itemCount
        val itemsCount = data.size
        list.addAll(data)
        notifyItemRangeChanged(positionStart, itemsCount)
    }

    /**
     *
     * @param positionStart Int
     * @param data List<T>?
     */
    fun addData(positionStart: Int, data: List<T>?) {
        if (data == null || data.isEmpty()) {
            return
        }

        val itemsCount = data.size
        //如果list的size小余 start+count，则默认添加到后面
        if (list.size < positionStart + itemsCount) {
            addData(data)
        } else {
            for (i in positionStart until positionStart + itemsCount) {
                list[i] = data[i - positionStart]
            }
            notifyItemRangeChanged(positionStart, itemsCount)
        }

    }

    //增加一条数据
    fun addData(data: T?) {
        if (data == null) {
            return
        }
        val positionStart = itemCount
        val itemsCount = 1
        list.add(data)
        notifyItemRangeChanged(positionStart, itemsCount)
    }

    //下拉刷新使用,如果null，需要清除list
    fun setData(data: List<T>?) {
        list.clear()
        if (data == null || data.isEmpty()) {
            return
        }
        list.addAll(data)
        notifyDataSetChanged()
    }

    fun setCheckBoxVisibility(visibility: Boolean) {
        this.checkBoxVisibility = visibility
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = if (list.isNotEmpty()) list.size else 0

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        onBindViewHolderImpl(holder, position, list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        this.context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(getLayoutId(viewType), parent, false)
        return BaseViewHolder(view)
    }

    abstract fun getLayoutId(viewType: Int): Int

    abstract fun onBindViewHolderImpl(holder: BaseViewHolder, position: Int, t: T)

    open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    companion object {
        const val TYPE_0 = 0
        const val TYPE_1 = 1
        const val TYPE_2 = 2
    }
}

