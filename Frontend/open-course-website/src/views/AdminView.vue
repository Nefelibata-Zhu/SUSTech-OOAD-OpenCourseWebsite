<template>
  <div>
    <el-container>
      <!-- Sidebar Menu -->
      <el-aside width="200px">
        <el-menu :default-active="activeMenu" class="menu-style" @select="handleMenuSelect">
          <el-menu-item index="1">待审核课程</el-menu-item>
          <el-menu-item index="2">热门课程</el-menu-item>
          <el-menu-item index="3">全部课程</el-menu-item>
        </el-menu>
      </el-aside>

      <!-- Main Content -->
      <el-main>
        <!-- Admin Title (placed in the left top corner) -->
        <div class="admin-title">
          管理员中心
        </div>

        <h2 style="margin-bottom: 20px;">待审核课程</h2>

        <!-- Wrap the v-for inside a div and apply v-if to the wrapper -->
        <div v-if="activeMenu === '1'">
          <el-row :gutter="20" v-for="course in courses" :key="course.courseID">
            <el-col :span="24">
              <el-card>
                <div style="display: flex; justify-content: space-between; align-items: center;">
                  <div style="display: flex; align-items: center;">
                    <h4>{{ course.courseName }}</h4>
                    <!-- Display the courseType with color on the right -->
                    <el-tag :type="getChapterColor(course.courseType)" disable-transitions style="margin-left: 10px;">
                      {{ course.courseType }}
                    </el-tag>
                  </div>
                  <el-button-group>
                    <el-button @click="approveCourse(course)">
                      <el-icon><check /></el-icon> 通过
                    </el-button>
                    <el-button @click="rejectCourse(course)">
                      <el-icon><close /></el-icon> 不通过
                    </el-button>
                  </el-button-group>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'AdminView',
  data() {
    return {
      activeMenu: '1', // Default to "待审核课程"
      courses: [
        { courseID: 1, courseName: 'Java程序设计与基础', courseType: '公开课' },
        { courseID: 2, courseName: '面向对象设计与分析', courseType: '半公开课' },
        { courseID: 3, courseName: '创新实践I', courseType: '非公开课' }
      ]
    };
  },
  methods: {
    getChapterColor(type) {
      switch (type) {
        case '公开课':
          return 'success';  // Green
        case '半公开课':
          return 'warning';  // Yellow
        case '非公开课':
          return 'info';     // Blue
        default:
          return 'primary';  // Default color
      }
    },
    approveCourse(course) {
      console.log(`课程 "${course.courseName}" 已通过审核`);
      // Implement approval logic here
    },
    rejectCourse(course) {
      console.log(`课程 "${course.courseName}" 未通过审核`);
      // Implement rejection logic here
    },
    handleMenuSelect(index) {
      this.activeMenu = index;
    }
  }
};
</script>

<style scoped>
.menu-style {
  height: 100%;
}

.el-card {
  margin-bottom: 20px;
}

/* Add this style for positioning the title in the left top corner */
.admin-title {
  position: absolute;
  top: 20px;
  left: 20px;
  font-size: 30px;
  font-weight: bold;
  color: #333;
}
</style>
