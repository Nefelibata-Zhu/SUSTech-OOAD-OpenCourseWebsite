<template>
  <div>
    <el-container style="height: 100vh;">
      <!-- Sidebar Menu for Popular Courses -->
      <el-aside width="200px" class="sidebar">
        <el-menu :default-active="activeMenu" class="menu-style" @select="handleMenuSelect">
          <el-menu-item index="2">热门课程</el-menu-item>
          <el-menu-item index="3">热门老师</el-menu-item>
          <el-menu-item index="4">AI问答</el-menu-item>
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

        <!-- Courses List -->
        <div class="courses-list">
          <div v-if="activeTab === 'day'">
            <div v-for="(course, index) in dayRanking" :key="course.courseID" class="course-item">
              <span>{{ index + 1 }}. {{ course.courseName }} | 讲师: {{ course.instructor }} | 学习人数: {{ course.learners }}</span>
              <el-button @click="viewCourse(course)" size="mini" class="view-button">查看课程</el-button>
            </div>
          </div>

          <div v-if="activeTab === 'week'">
            <div v-for="(course, index) in weekRanking" :key="course.courseID" class="course-item">
              <span>{{ index + 1 }}. {{ course.courseName }} | 讲师: {{ course.instructor }} | 学习人数: {{ course.learners }}</span>
              <el-button @click="viewCourse(course)" size="mini" class="view-button">查看课程</el-button>
            </div>
          </div>

          <div v-if="activeTab === 'total'">
            <div v-for="(course, index) in totalRanking" :key="course.courseID" class="course-item">
              <span>{{ index + 1 }}. {{ course.courseName }} | 讲师: {{ course.instructor }} | 学习人数: {{ course.learners }}</span>
              <el-button @click="viewCourse(course)" size="mini" class="view-button">查看课程</el-button>
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
      activeMenu: '2',
      activeTab: 'day',
      dayRanking: [
        { courseID: 1, courseName: 'Python数据分析实战', instructor: '王五', learners: '12,345' },
        { courseID: 2, courseName: 'Web开发入门到精通', instructor: '赵六', learners: '10,678' },
        { courseID: 3, courseName: '机器学习基础', instructor: '李四', learners: '9,876' }
      ],
      weekRanking: [
        { courseID: 1, courseName: 'Python数据分析实战', instructor: '王五', learners: '25,000' },
        { courseID: 2, courseName: 'Web开发入门到精通', instructor: '赵六', learners: '20,000' },
        { courseID: 3, courseName: '人工智能基础', instructor: '李四', learners: '15,000' }
      ],
      totalRanking: [
        { courseID: 1, courseName: 'Python数据分析实战', instructor: '王五', learners: '100,000' },
        { courseID: 2, courseName: 'Web开发入门到精通', instructor: '赵六', learners: '85,000' },
        { courseID: 3, courseName: '机器学习基础', instructor: '李四', learners: '75,000' }
      ]
    };
  },
  methods: {
    handleMenuSelect(index) {
      this.activeMenu = index;
    },
    changeTab(tab) {
      this.activeTab = tab;
    },
    viewCourse(course) {
      console.log(`查看课程: ${course.courseName}`);
    }
  }
};
</script>

<style scoped>
.menu-style {
  height: 100%;
}

.el-button {
  margin-right: 10px;
}

.leaderboard-header {
  margin-bottom: 20px;
}

.courses-list {
  margin-top: 20px;
  border-radius: 10px;
  overflow: hidden;
  background-color: #fff;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
}

.course-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #f0f0f0;
}

.course-item:last-child {
  border-bottom: none;
}

.tab-button {
  border-radius: 20px;
  margin-right: 10px;
}

.view-button {
  margin-left: 10px;
}

.el-menu {
  border-right: 1px solid #dcdfe6;
}

.el-tag {
  margin-left: 10px;
}

.sidebar {
  background-color: #f0f0f0;
  padding: 20px;
  height: 100%;
}

.el-menu-item {
  font-size: 16px;
}

.el-container {
  height: 100%;
  display: flex;
}

.el-aside {
  background-color: #f8f8f8;
  height: 100%;
}

.el-main {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  overflow: auto;
}
</style>
