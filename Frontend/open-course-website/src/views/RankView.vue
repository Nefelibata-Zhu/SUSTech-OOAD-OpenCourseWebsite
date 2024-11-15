<template>
  <div>
    <el-container style="height: 100vh;">
      <!-- Sidebar Menu for Popular Courses and Teachers -->
      <el-aside width="200px" class="sidebar">
        <el-menu :default-active="activeMenu" class="menu-style" @select="handleMenuSelect">
          <el-menu-item index="1">热门课程</el-menu-item>
          <el-menu-item index="2">热门老师</el-menu-item>
        </el-menu>
      </el-aside>

      <!-- Main Content -->
      <el-main style="height: 100%; padding: 20px;">
        <!-- Title for the leaderboard section -->
        <div class="leaderboard-header">
          <el-button
              :type="activeTab === 'day' ? 'danger' : 'primary'"
              @click="changeTab('day')"
              class="tab-button"
          >日榜</el-button>
          <el-button
              :type="activeTab === 'week' ? 'danger' : 'primary'"
              @click="changeTab('week')"
              class="tab-button"
          >周榜</el-button>
          <el-button
              :type="activeTab === 'total' ? 'danger' : 'primary'"
              @click="changeTab('total')"
              class="tab-button"
          >总榜</el-button>
        </div>

        <!-- Content for Popular Courses or Teachers -->
        <div v-if="activeMenu === '1'" class="courses-list">
          <!-- Courses List for Day, Week, and Total Rankings -->
          <div v-if="activeTab === 'day'">
            <div v-for="(course, index) in dayRanking" :key="course.courseID" class="course-item" :class="{'gold': index === 0, 'silver': index === 1, 'bronze': index === 2}">
              <span>{{ index + 1 }}. {{ course.courseName }} | 评分: {{ course.rating }} ⭐ | 讲师: {{ course.instructor }} | 学习人数: {{ course.learners }}</span>
              <el-button @click="viewCourse(course)" size="mini" class="view-button">查看课程</el-button>
            </div>
          </div>

          <div v-if="activeTab === 'week'">
            <div v-for="(course, index) in weekRanking" :key="course.courseID" class="course-item" :class="{'gold': index === 0, 'silver': index === 1, 'bronze': index === 2}">
              <span>{{ index + 1 }}. {{ course.courseName }} | 评分: {{ course.rating }} ⭐ | 讲师: {{ course.instructor }} | 学习人数: {{ course.learners }}</span>
              <el-button @click="viewCourse(course)" size="mini" class="view-button">查看课程</el-button>
            </div>
          </div>

          <div v-if="activeTab === 'total'">
            <div v-for="(course, index) in totalRanking" :key="course.courseID" class="course-item" :class="{'gold': index === 0, 'silver': index === 1, 'bronze': index === 2}">
              <span>{{ index + 1 }}. {{ course.courseName }} | 评分: {{ course.rating }} ⭐ | 讲师: {{ course.instructor }} | 学习人数: {{ course.learners }}</span>
              <el-button @click="viewCourse(course)" size="mini" class="view-button">查看课程</el-button>
            </div>
          </div>
        </div>

        <div v-if="activeMenu === '2'" class="teachers-list">
          <!-- Teachers List for Day, Week, and Total Rankings -->
          <div v-if="activeTab === 'day'">
            <div v-for="(teacher, index) in dayTeacherRanking" :key="teacher.teacherID" class="teacher-item" :class="{'gold': index === 0, 'silver': index === 1, 'bronze': index === 2}">
              <img src="path-to-placeholder-image" alt="头像" class="teacher-avatar">
              <span>{{ index + 1 }}. {{ teacher.teacherName }} | 评分: {{ teacher.rating }} ⭐ | 课程数: {{ teacher.courseCount }} | 学生数: {{ teacher.studentCount }}</span>
            </div>
          </div>

          <div v-if="activeTab === 'week'">
            <div v-for="(teacher, index) in weekTeacherRanking" :key="teacher.teacherID" class="teacher-item" :class="{'gold': index === 0, 'silver': index === 1, 'bronze': index === 2}">
              <img src="path-to-placeholder-image" alt="头像" class="teacher-avatar">
              <span>{{ index + 1 }}. {{ teacher.teacherName }} | 评分: {{ teacher.rating }} ⭐ | 课程数: {{ teacher.courseCount }} | 学生数: {{ teacher.studentCount }}</span>
            </div>
          </div>

          <div v-if="activeTab === 'total'">
            <div v-for="(teacher, index) in totalTeacherRanking" :key="teacher.teacherID" class="teacher-item" :class="{'gold': index === 0, 'silver': index === 1, 'bronze': index === 2}">
              <img src="path-to-placeholder-image" alt="头像" class="teacher-avatar">
              <span>{{ index + 1 }}. {{ teacher.teacherName }} | 评分: {{ teacher.rating }} ⭐ | 课程数: {{ teacher.courseCount }} | 学生数: {{ teacher.studentCount }}</span>
            </div>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeMenu: '1', // Default to "热门课程"
      activeTab: 'day',
      dayRanking: [
        { courseID: 1, courseName: 'Python数据分析实战', instructor: '王五', learners: '12,345', rating: 4.9 },
        { courseID: 2, courseName: 'Web开发入门到精通', instructor: '赵六', learners: '10,678', rating: 4.7 },
        { courseID: 3, courseName: '机器学习基础', instructor: '李四', learners: '9,876', rating: 4.5 },
        { courseID: 4, courseName: '高级Java编程', instructor: '孙八', learners: '8,765', rating: 4.6 },
        { courseID: 5, courseName: '数据结构与算法', instructor: '周九', learners: '7,654', rating: 4.8 }
      ],
      weekRanking: [
        { courseID: 1, courseName: 'Python数据分析实战', instructor: '王五', learners: '25,000', rating: 4.9 },
        { courseID: 2, courseName: 'Web开发入门到精通', instructor: '赵六', learners: '20,000', rating: 4.7 },
        { courseID: 3, courseName: '人工智能基础', instructor: '李四', learners: '15,000', rating: 4.5 },
        { courseID: 4, courseName: '高级Java编程', instructor: '孙八', learners: '10,000', rating: 4.6 },
        { courseID: 5, courseName: '数据结构与算法', instructor: '周九', learners: '9,000', rating: 4.8 }
      ],
      totalRanking: [
        { courseID: 1, courseName: 'Python数据分析实战', instructor: '王五', learners: '100,000', rating: 4.9 },
        { courseID: 2, courseName: 'Web开发入门到精通', instructor: '赵六', learners: '85,000', rating: 4.7 },
        { courseID: 3, courseName: '机器学习基础', instructor: '李四', learners: '75,000', rating: 4.5 },
        { courseID: 4, courseName: '高级Java编程', instructor: '孙八', learners: '70,000', rating: 4.6 },
        { courseID: 5, courseName: '数据结构与算法', instructor: '周九', learners: '65,000', rating: 4.8 }
      ],
      // Add teacher data for rankings
      dayTeacherRanking: [
        { teacherID: 1, teacherName: '张三', rating: '4.9', courseCount: 5, studentCount: '1,200' },
        { teacherID: 2, teacherName: '李四', rating: '4.8', courseCount: 3, studentCount: '950' },
        { teacherID: 3, teacherName: '王五', rating: '4.7', courseCount: 6, studentCount: '1,100' }
      ],
      weekTeacherRanking: [
        { teacherID: 1, teacherName: '张三', rating: '4.9', courseCount: 5, studentCount: '1,500' },
        { teacherID: 2, teacherName: '李四', rating: '4.8', courseCount: 3, studentCount: '1,000' },
        { teacherID: 3, teacherName: '王五', rating: '4.7', courseCount: 6, studentCount: '1,200' }
      ],
      totalTeacherRanking: [
        { teacherID: 1, teacherName: '张三', rating: '4.9', courseCount: 5, studentCount: '10,000' },
        { teacherID: 2, teacherName: '李四', rating: '4.8', courseCount: 3, studentCount: '7,000' },
        { teacherID: 3, teacherName: '王五', rating: '4.7', courseCount: 6, studentCount: '8,000' }
      ]
    };
  },
  methods: {
    handleMenuSelect(index) {
      this.activeMenu = index;
      this.activeTab = 'day'; // Reset to day tab when menu changes
    },
    changeTab(tab) {
      this.activeTab = tab;
    },
    viewCourse(course) {
      this.$message.info(`查看课程: ${course.courseName}`);
    }
  }
};
</script>

<style scoped>
.sidebar {
  background-color: #f4f4f4;
  padding: 20px;
}
.menu-style {
  border-right: 1px solid #ddd;
}
.tab-button {
  margin-right: 10px;
  margin-bottom: 10px;
}
.leaderboard-header {
  margin-bottom: 20px;
}
.course-item, .teacher-item {
  display: flex;
  justify-content: space-between;
  padding: 10px;
  margin: 5px 0;
  background: #fff;
  border: 1px solid #ddd;
  border-radius: 5px;
}
.course-item.gold, .teacher-item.gold {
  background-color: #ffdf00;
}
.course-item.silver, .teacher-item.silver {
  background-color: #c0c0c0;
}
.course-item.bronze, .teacher-item.bronze {
  background-color: #cd7f32;
}
.view-button {
  margin-left: 10px;
}
.teacher-item {
  display: flex;
  justify-content: flex-start; /* 将文字顶左对齐 */
  align-items: center; /* 垂直居中 */
  padding: 10px;
  margin: 5px 0;
  background: #fff;
  border: 1px solid #ddd;
  border-radius: 5px;
}

.teacher-avatar {
  width: 40px; /* 设置头像大小 */
  height: 40px; /* 设置头像大小 */
  border-radius: 50%; /* 使头像圆形 */
  margin-right: 10px; /* 头像与文字之间的间距 */
}
</style>
