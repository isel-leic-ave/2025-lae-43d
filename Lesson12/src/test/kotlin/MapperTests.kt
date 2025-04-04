package isel.lae.mapper

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class MapperTests {
    val courseInternal = CourseInternal("LAE", 4, "LEIC", 80)
    val courseExternal = CourseExt("LAE", 4, "LEIC")
    val anotherCourseExternal = AnotherCourseExternal("LAE", 4, "LEIC")

    @Test
    fun `Should Map ExternalCourse To InternalCourse`() {
        var cInternal = mapTo(courseExternal, CourseInternal::class)
        assertNotNull(cInternal)
        assertEquals(CourseInternal::class, cInternal::class)

        val ci = cInternal as CourseInternal
        assertEquals(courseExternal.name, ci.name)
        assertEquals(courseExternal.semester, ci.semester)
        assertEquals(courseExternal.programme, ci.programme)
        assertEquals(-1, ci.numStudents)
    }

    @Test
    fun `Should Map InternalCourse To ExternalCourse`() {
        var cExt = mapTo(courseInternal, CourseExt::class)
        assertNotNull(cExt)
        assertEquals(CourseExt::class, cExt::class)

        val ce = cExt as CourseExt
        assertEquals(courseInternal.name, ce.name)
        assertEquals(courseInternal.semester, ce.semester)
        assertEquals(courseInternal.programme, ce.programme)
    }

    @Test
    fun `Should Map CourseExt To AnotherCourseExternal`() {
        var anCExt = mapTo(courseExternal, AnotherCourseExternal::class)
        assertNotNull(anCExt)
        assertEquals(AnotherCourseExternal::class, anCExt::class)

        val ce = anCExt as AnotherCourseExternal
        assertEquals(anotherCourseExternal.courseName, ce.courseName)
        assertEquals(anotherCourseExternal.semester, ce.semester)
        assertEquals(anotherCourseExternal.programme, ce.programme)
    }

    @Test
    fun `Should Map CourseExternalWithTeacher To CourseInternalWithTeacher`() {
        val courseExternalWithTeacher = CourseExternalWithTeacher("LAE", 4, "LEIC", TeacherExternal("Luis Falcão", "luis.falcao@isel.pt"))
        var cIntWithTeacher = mapTo(courseExternalWithTeacher, CourseInternalWithTeacher::class)
        assertNotNull(cIntWithTeacher)
        assertEquals(CourseInternalWithTeacher::class, cIntWithTeacher::class)

        val cIntWTeacher = cIntWithTeacher as CourseInternalWithTeacher

        assertNotNull(cIntWTeacher.teacher)

        val teacherExt = courseExternalWithTeacher.teacher
        val teacherInt = cIntWTeacher.teacher
        assertEquals(teacherExt.name, teacherInt.name)
        assertEquals(teacherExt.email, teacherInt.email)


    }
}