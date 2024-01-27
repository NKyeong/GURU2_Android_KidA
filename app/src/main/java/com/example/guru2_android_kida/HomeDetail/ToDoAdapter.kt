package com.example.guru2_android_kida.HomeDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2_android_kida.R
import com.example.guru2_android_kida.databinding.HomeToDoListBinding

// 여기서 todoList는 dataset
class ToDoAdapter(private val todoList: MutableList<ToDoItem>) : RecyclerView.Adapter<ToDoAdapter.ToDoItemViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_to_do_list, parent, false)
        return ToDoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
        val todoItem = todoList[position]

        // 체크박스 상태 설정
        holder.checkBox.isChecked = todoItem.isChecked

        // 에디트텍스트 내용 설정 및 체크박스에 따라 편집 가능/불가능 설정
        holder.editText.setText(todoItem.todoText)
        holder.editText.isEnabled = !todoItem.isChecked

        // 체크박스 상태 변경 이벤트 처리
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            todoItem.isChecked = isChecked
            holder.editText.isEnabled = !isChecked
        }

        // 에디트텍스트 포커스 이벤트 처리
        holder.editText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                // 에디트텍스트의 포커스가 사라질 때 할 일 내용을 모델에 업데이트
                todoItem.todoText = holder.editText.text.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    // 뷰홀더 클래스
    class ToDoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        val editText: EditText = itemView.findViewById(R.id.editText)
    }

    fun addItem(item: ToDoItem) {
        todoList.add(item)
        notifyItemInserted(todoList.size - 1)
    }

    fun updateList(newList: List<ToDoItem>) {
        todoList.clear()
        todoList.addAll(newList)
        notifyDataSetChanged()
    }
}