// IStudentService.aidl
package com.kunminx.learningaidlservice;

// Declare any non-default types here with import statements
import com.kunminx.learningaidlservice.Student;//TODO:这里要不要呢
interface IStudentService {
    Student getStudentById(int id);
}
