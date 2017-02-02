package com.netcracker.backend.service.impl;

import com.netcracker.backend.dao.Dao;
import com.netcracker.backend.exceptions.DaoException;
import com.netcracker.backend.exceptions.ServErrorCode;
import com.netcracker.backend.exceptions.ServException;
import com.netcracker.backend.service.StudentService;
import com.netcracker.entities.Student;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {
    private static Logger log = Logger.getLogger(StudentServiceImpl.class);

    @Autowired
    private Dao<Student, Integer> studentDao;

    StudentServiceImpl() {
    }

    /**
     * This method geting all students.
     *
     * @return students.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public List<Student> getAllStudent() throws ServException {
        List<Student> students;
        try {
            students = studentDao.getAll();
            log.info("Getting all students:" + students);
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_000);
        }
        return students;
    }

    /**
     * This method deleting student by id.
     *
     * @param id Student id.
     * @return true if student successfully is deleted.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Boolean deleteStudentById(Integer id) throws ServException {
        Student student;
        try {
            student = studentDao.get(id);
            studentDao.delete(student);
            log.info("Deleting student:" + student);
            return true;
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_002);
        }
    }

    /**
     * This method saving new student.
     *
     * @param student
     * @return true if student is successfully saved.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Student saveStudent(Student student) throws ServException {
        try {
            Integer studentId = studentDao.add(student);
            log.info("Adding student:" + student);
            Student savedStudent = getStudentById(studentId);
            return savedStudent;
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_003);
        }
    }

    /**
     * This method getting student by id.
     *
     * @param id Student id.
     * @return student.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Student getStudentById(Integer id) throws ServException {
        Student student;
        try {
            student = studentDao.get(id);
            log.info("Getting student:" + student);
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_004);
        }
        return student;
    }

    /**
     * This method updating student.
     *
     * @param student
     * @return true if student successfully is updated.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Student updateStudent(Integer id, Student student) throws ServException {
        try {
            if (id != null) {
                Student preparedStudent = Student.builder().id(id).name(student.getName()).surname(student.getSurname()).build();
                studentDao.update(preparedStudent);
                log.info("Updating student:" + student);
                return getStudentById(id);
            }
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_006);
        } catch (ServException ex) {
            throw new ServException(ex, ServErrorCode.NC_SERV_004);
        }
        return null;
    }

    /**
     * This method finding students.
     *
     * @param param String parametr for find students.
     * @return students
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    public List<Student> findStudents(String param) throws ServException {
        List<Student> students;
        param = '%' + param + '%';
        try {
            Query query = studentDao.getQuery("from Student stud where stud.fio like  :param " +
                    "or stud.group.facult like :param");
            query.setParameter("param", param);
            students = query.list();
            log.info("Finding students:" + students);
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_007);
        }
        return students;
    }


}
