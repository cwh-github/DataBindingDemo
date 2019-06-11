package com.chainspower.mytab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chainspower.model.ItemInfo
import com.chainspower.mytab.databinding.ItemDataBinding
import kotlinx.android.synthetic.main.activity_data_binding_recycler_view.*
import java.util.*

class DataBindingRecyclerViewActivity : AppCompatActivity() {

    lateinit var mList: MutableList<ItemInfo>
    lateinit var mObservableList: ObservableArrayList<ItemInfo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_binding_recycler_view)
        mList = ArrayList()
        mObservableList = ObservableArrayList()
        for (i in 0 until 15) {
            mList.add(ItemInfo("name is $i", Random().nextInt()))
            mObservableList.add(ItemInfo("Observable name is $i", Random().nextInt()))
        }
        mRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        //mRecyclerView.adapter = NormalAdapter(mList)
        val adapter = ObservableAdapter(mObservableList)
        mObservableList.addOnListChangedCallback(ObservableChangeListener(adapter))
        mRecyclerView.adapter = adapter

        initClickListener()
    }

    private fun initClickListener() {
        mBtnAddItem.setOnClickListener {
            val position = Random().nextInt(mObservableList.size)
            mObservableList.add(position, ItemInfo("name is $position", Random().nextInt()))
        }

        mBtnAddItemList.setOnClickListener {
            val list = ArrayList<ItemInfo>()
            for (i in 0 until 5) {
                list.add(ItemInfo("name is $i", Random().nextInt()))
                //mObservableList.add(ItemInfo("Observable name is $i", Random().nextInt()))
            }
            mObservableList.addAll(list)
        }

        mBtnRemoveItem.setOnClickListener {
            if (mObservableList.size > 1) {
                mObservableList.removeAt(Random().nextInt(mObservableList.size))
            } else {
                Toast.makeText(this, "不能全部数据都移除", Toast.LENGTH_SHORT).show()
            }
        }

        mBtnUpdateItem.setOnClickListener {

            val index = Random().nextInt(mObservableList.size)
            val item = mObservableList[index]
            item.name = "Update Name"
            item.seed = Random().nextInt()
            mObservableList[index] = item
        }
    }
}

class NormalAdapter(var mList: MutableList<ItemInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mBinding = DataBindingUtil.inflate<ItemDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_databinding,
            parent,
            false
        )
        return MViewHolder(mBinding.root, mBinding)
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mViewHolder = holder as MViewHolder
        mViewHolder.mBinding.item = mList[position]
    }

    inner class MViewHolder(itemView: View, var mBinding: ItemDataBinding) : RecyclerView.ViewHolder(itemView)

}


class ObservableAdapter(var mList: ObservableArrayList<ItemInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mBinding = DataBindingUtil.inflate<ItemDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_databinding,
            parent,
            false
        )
        return MViewHolder(mBinding.root, mBinding)
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mViewHolder = holder as MViewHolder
        mViewHolder.mBinding.item = mList[position]
    }

    inner class MViewHolder(itemView: View, var mBinding: ItemDataBinding) : RecyclerView.ViewHolder(itemView)
}

//当ObservableList改变时的监听
class ObservableChangeListener<T : ObservableList<*>?>(var mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) :
    ObservableList.OnListChangedCallback<T>() {
    override fun onChanged(sender: T) {
        mAdapter.notifyDataSetChanged()
    }

    override fun onItemRangeRemoved(sender: T, positionStart: Int, itemCount: Int) {
        mAdapter.notifyItemRangeRemoved(positionStart, itemCount)
    }

    override fun onItemRangeMoved(sender: T, fromPosition: Int, toPosition: Int, itemCount: Int) {
        mAdapter.notifyItemRangeRemoved(fromPosition, itemCount)
        mAdapter.notifyItemRangeInserted(toPosition, itemCount)
    }

    override fun onItemRangeInserted(sender: T, positionStart: Int, itemCount: Int) {
        mAdapter.notifyItemRangeInserted(positionStart, itemCount)
    }

    override fun onItemRangeChanged(sender: T, positionStart: Int, itemCount: Int) {
        mAdapter.notifyItemRangeChanged(positionStart, itemCount)
    }

}
