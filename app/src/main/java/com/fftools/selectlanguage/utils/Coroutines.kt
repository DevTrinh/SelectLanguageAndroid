package com.fftools.selectlanguage.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.File

object Coroutines {

    /*Sử dụng cho các tác vụ tính toán trung bình và không đòi hỏi tài nguyên I/O quá mức.
    Được tối ưu hóa cho việc xử lý nhiều tác vụ tính toán nhanh chóng*/
    fun default(work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.Default).launch {
        work()
    }

    /*Sử dụng cho các tác vụ I/O-bound như đọc/ghi file,
    truy vấn cơ sở dữ liệu, hoặc tương tác với mạng.
    Được tối ưu hóa để chịu tải nhiều công việc I/O.*/
    fun io(work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.IO).launch {
        work()
    }

    suspend fun ioAwaitIntArray(work: () -> IntArray?): IntArray? {
        return CoroutineScope(Dispatchers.IO).async {
            work()
        }.await()
    }

    suspend fun ioAwait(work: () -> File?): File? {
        return CoroutineScope(Dispatchers.IO).async {
            work()
        }.await()
    }

    suspend fun ioAwaitAny(work: () -> Any?): Any? {
        return CoroutineScope(Dispatchers.IO).async {
            work()
        }.await()
    }

    /*Sử dụng để thực hiện các tác vụ trên luồng chính của giao diện người dùng (UI thread).
    Lưu ý: Chỉ có thể sử dụng trên Android khi bạn đang làm việc với giao diện người dùng.*/
    fun main(work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.Main).launch {
        work()
    }

    fun delay(time: Long, work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.Main).launch {
        kotlinx.coroutines.delay(time)
        work()
    }

}