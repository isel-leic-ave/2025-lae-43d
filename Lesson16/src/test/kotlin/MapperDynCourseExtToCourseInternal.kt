package isel.lae.li43d.mapper

class MapperDynCourseExtToCourseInternal() : Mapper<CourseExt, CourseInternal> {
    override fun mapTo(courseExt: CourseExt): CourseInternal {
        return CourseInternal(courseExt.name, courseExt.semester, courseExt.programme)
    }
}