package com.example.notesapp.presentation.todo.addTodo

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.CustomPopupMenu
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.core.snackbar
import com.example.notesapp.data.todo.models.Todo
import com.example.notesapp.databinding.FragmentAddTodoBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class AddTodoFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddTodoBinding
    private val viewModel by viewModels<AddTodoViewModel>()

    private var date = Calendar.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        createNotificationChannel()
        initObserves()
    }

    private fun initView() {
        activity!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED)


        binding.imageView.setOnClickListener {
            createTodo()
            scheduleNotification()
            binding.edDescrtiptionAddTodo.visibility = View.GONE
        }

        binding.imgDescription.setOnClickListener {
            binding.edDescrtiptionAddTodo.visibility = View.VISIBLE
        }
        binding.imgCalendar.setOnClickListener {
            createDateAndTimePicker()
        }
        binding.imgColor.setOnClickListener {
            createPopupMenu()
        }
    }

    private fun initObserves() {
        viewModel.todo.observe(viewLifecycleOwner) {
            binding.edTitle.setText(it.title)
            binding.edDescrtiptionAddTodo.setText(it.description)
            viewModel.changeSelectedColor(it.color)
        }
    }

    private fun createTodo() {
        val title = binding.edTitle.text.toString()
        val description = binding.edDescrtiptionAddTodo.text.toString()
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())

        if (title.isEmpty()) {
            snackbar(binding.root, "Введите текст")
        } else {
            val todo = Todo(
                0, title, description, currentDate, viewModel.selectedColorTodo.value!!, viewModel.currentPriority.value!!
            )
            viewModel.createTodo(todo)

            findNavController().navigate(R.id.action_addTodoFragment_to_todoFragment)
        }
    }

    private fun createDateAndTimePicker() {
        val currentDate = Calendar.getInstance()
        DatePickerDialog(
            context!!,
            { _, year, monthOfYear, dayOfMonth ->
                date.set(year, monthOfYear, dayOfMonth)
                TimePickerDialog(
                    context,
                    { _, hourOfDay, minute ->
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        date.set(Calendar.MINUTE, minute)

                    },
                    currentDate.get(Calendar.HOUR_OF_DAY),
                    currentDate.get(Calendar.MINUTE),
                    true
                ).show()

            },
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DATE)
        ).show()
    }

    private fun createNotificationChannel() {
        val name = "Notif Channel"
        val desc = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager =
            context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun scheduleNotification() {

        val intent = Intent(requireContext(), Notification::class.java)
        val title = binding.edTitle.text.toString()
        intent.putExtra(titleExtra, title)


        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = date.timeInMillis
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )

    }

    private fun createPopupMenu() {
        val wrapper: Context = ContextThemeWrapper(requireContext(), R.style.PopupMenu)
        val popup = CustomPopupMenu(wrapper, binding.root)
        popup.menu.apply {
            add(Menu.NONE, 0, Menu.NONE, "Высокий").apply {
                setIcon(R.drawable.ic_baseline_flag_24)
            }
            add(Menu.NONE, 1, Menu.NONE, "Средний").apply {
                setIcon(R.drawable.ic_baseline_flag_2422)
            }
            add(Menu.NONE, 2, Menu.NONE, "Низкий").apply {
                setIcon(R.drawable.ic_baseline_flag_24222)
            }
            add(Menu.NONE, 3, Menu.NONE, "Нет").apply {
                setIcon(R.drawable.ic_baseline_flag_grey)
            }
        }
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                0 -> {
                    viewModel.changeSelectedColor("red")
                    viewModel.changeCurrentPriority(3)
                }
                1 -> {
                    viewModel.changeSelectedColor("yellow")
                    viewModel.changeCurrentPriority(2)
                }
                2 -> {
                    viewModel.changeSelectedColor("blue")
                    viewModel.changeCurrentPriority(1)
                }
                3 -> {
                    viewModel.changeSelectedColor("grey")
                    viewModel.changeCurrentPriority(0)
                }
            }
            return@setOnMenuItemClickListener true
        }
        popup.show()
    }
}
