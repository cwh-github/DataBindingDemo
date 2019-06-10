package com.chainspower.mytab

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_dialog_fragment.*
import kotlinx.android.synthetic.main.dialog_fragment.*

class DialogFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_fragment)

        mBtnDialog.setOnClickListener {
            val fragmentDialog = MyDialogFragment()
            fragmentDialog.show(supportFragmentManager, "MyFragment")
        }

        mBtnDialog1.setOnClickListener {

            val fragmentDialog = MyDialogFragment1()
            fragmentDialog.show(supportFragmentManager, "MyFragment")
        }
    }
}

class MyDialogFragment : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(STYLE_NO_TITLE)
        return inflater?.inflate(R.layout.dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBt1.setOnClickListener {
            Toast.makeText(activity, "BTN 1 show", Toast.LENGTH_SHORT).show()
        }

        mBtn2.setOnClickListener {
            Toast.makeText(activity, "BTN 2 show", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }
}

class MyDialogFragment1 : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_fragment, null)
        val builder = AlertDialog.Builder(activity)
        builder.setView(view)
        builder.setPositiveButton(
            "Confirm"
        ) { _, _ -> Toast.makeText(activity, "Confirm", Toast.LENGTH_SHORT).show() }
        builder.setNegativeButton("Cancel") { _, _ ->Toast.makeText(activity, "Cancel", Toast.LENGTH_SHORT).show()
        }
        return builder.create()
    }

}
