package com.example.myapplication

import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class DataAdapterWithHeader(private var mDatas:List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val TYPE_HEADER = 0 //说明是带有Header的

    val TYPE_FOOTER = 1 //说明是带有Footer的

    val TYPE_NORMAL = 2 //说明是不带有header和footer的

    //HeaderView, FooterView
    private var mHeaderView: View? = null
    private var mFooterView: View? = null

    //HeaderView和FooterView的get和set函数
    fun getHeaderView(): View? {
        return mHeaderView
    }

    fun setHeaderView(headerView: View?) {
        mHeaderView = headerView
        notifyItemInserted(0)
    }

    fun getFooterView(): View? {
        return mFooterView
    }

    fun setFooterView(footerView: View?) {
        mFooterView = footerView
        notifyItemInserted(getItemCount() - 1)
    }

    /** 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    * */
    override fun getItemViewType(position: Int): Int {
        if ( mHeaderView != null && position == 0) {
            //第一个item应该加载Header
            return TYPE_HEADER
        }
        if( mFooterView != null && position == getItemCount() - 1){
            return TYPE_FOOTER
        }
        return TYPE_NORMAL
    }

    //创建View，如果是HeaderView或者是FooterView，直接在Holder中返回
    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return ListHolder(mHeaderView!!)
        }
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return ListHolder(mFooterView!!)
        }
        val layout: View =
            LayoutInflater.from(parent.context).inflate(R.layout.cyclerview_item, parent, false)
        return ListHolder(layout)
    }

    //绑定View，这里是根据返回的这个position的类型，从而进行绑定的，   HeaderView和FooterView, 就不同绑定了
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            if (holder is ListHolder) {
                var realPosition = position
                if( mHeaderView != null ){
                    //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                    realPosition = realPosition -1
                }
                holder.tv?.text = mDatas[realPosition]
            }
            return
        } else{
            return
        }
    }

    //在这里面加载ListView中的每个item的布局
    inner class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView? = null

        init {
            //如果是headerview或者是footerview,直接返回
            if (itemView === mHeaderView) {

            }else if( itemView == mFooterView ){

            }else{
                tv = itemView.findViewById(R.id.item)
            }
        }
    }

    //返回View中Item的个数，这个时候，总的个数应该是ListView中Item的个数加上HeaderView和FooterView
    override fun getItemCount(): Int {
        return if (mHeaderView == null && mFooterView == null) {
            mDatas.size
        } else if (mHeaderView == null && mFooterView != null) {
            mDatas.size + 1
        } else if (mHeaderView != null && mFooterView == null) {
            mDatas.size + 1
        } else {
            mDatas.size + 2
        }
    }
}