package com.chainspower.mytab

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mRecyclerView.adapter = MyAdapter(this)
        mRecyclerView.addItemDecoration(MyItemDecoration())
    }

}

class MyAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = 20

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mViewHolder = holder as MyViewHolder
        mViewHolder.mTvText.text = "Position is $position"
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvText = itemView.findViewById<TextView>(R.id.mTvItem)
    }

}


class MyItemDecoration : RecyclerView.ItemDecoration() {

    val paint: Paint = Paint()
    var mDiverHight = 0

    init {
        paint.isAntiAlias = true
        paint.color = Color.GRAY
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) != 0 &&
            parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount - 1
        ) {
            outRect.top = 60
            outRect.left = 160
            mDiverHight = 60
        } else {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = 60
                outRect.left = 160
            }

            if (parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1) {
                outRect.top = 60
                outRect.bottom = 60
                outRect.left = 160
            }

        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val view = parent.getChildAt(0)
        val position=parent.getChildLayoutPosition(view)

        //draw Head
        paint.color = Color.GRAY
        paint.style = Paint.Style.FILL
        var drawTop = 0f
        val drawLeft = 160f
        val drawBottom = view.top
        val drawRight = parent.width

        val shouldTop=view.bottom-mDiverHight
        if(shouldTop<drawTop){
            drawTop=shouldTop.toFloat()
        }
        c.drawRect(drawLeft, drawTop, drawRight.toFloat(), drawTop+60f, paint)

        //draw text
        paint.color=Color.WHITE
        paint.textSize=25f
        c.drawText("Position is $position",view.left.toFloat(),(drawTop+60f)/2,paint)

    }


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        //左侧draw一条白线
        paint.color = Color.WHITE
        val drawTop = 0f
        val drawLeft = (parent.getChildAt(0).left / 2) - 10
        val drawRight = (parent.getChildAt(0).left / 2) + 10
        val drawBottom = parent.height
        c.drawRect(drawLeft.toFloat(), drawTop, drawRight.toFloat(), drawBottom.toFloat(), paint)

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildLayoutPosition(view)
            if (position == 0) {
                paint.color = Color.GRAY
                paint.style = Paint.Style.FILL
                val drawTop = 0f
                val drawLeft = 160f
                val drawBottom = view.top
                val drawRight = parent.width
                c.drawRect(drawLeft, drawTop, drawRight.toFloat(), drawBottom.toFloat(), paint)

                //画点
                val pointX = view.left / 2
                val pointY = (view.top + view.bottom) / 2
                paint.color = Color.CYAN
                paint.style = Paint.Style.FILL_AND_STROKE
                c.drawCircle(pointX.toFloat(), pointY.toFloat(), 25f, paint)

                //画字，作为item的title
                paint.color = Color.WHITE
                paint.textSize = 25f
                c.drawText("Position is $position", view.left.toFloat(), (view.top / 2).toFloat(), paint)

                continue
            }

            paint.color = Color.GRAY
            paint.style = Paint.Style.FILL

            val drawTop = view.top - mDiverHight
            val drawLeft = parent.paddingLeft + view.left
            val drawBottom = view.top
            val drawRight = parent.width - parent.paddingRight

            Log.d("Item", "view top is ${view.top}")

            c.drawRect(
                drawLeft.toFloat(), drawTop.toFloat(),
                drawRight.toFloat(), drawBottom.toFloat(), paint
            )

            if (position == parent.adapter!!.itemCount - 1) {
                paint.color = Color.GREEN
                paint.style = Paint.Style.FILL
                val drawTop = view.bottom.toFloat()
                val drawLeft = 0f
                val drawRight = parent.width
                val drawBottom = parent.height
                c.drawRect(drawLeft, drawTop, drawRight.toFloat(), drawBottom.toFloat(), paint)
            }

            //画点
            val pointX = view.left / 2
            val pointY = (view.top + view.bottom) / 2
            paint.color = Color.CYAN
            paint.style = Paint.Style.FILL_AND_STROKE
            c.drawCircle(pointX.toFloat(), pointY.toFloat(), 25f, paint)

            //画字，作为item的title
            paint.color = Color.WHITE
            val textY = view.bottom - view.height - (mDiverHight / 2)
            paint.textSize = 25f
            c.drawText("Position is $position", view.left.toFloat(), textY.toFloat(), paint)


        }
    }

}
