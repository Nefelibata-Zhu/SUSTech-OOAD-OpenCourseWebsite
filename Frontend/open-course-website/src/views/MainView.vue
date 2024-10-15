<template>
  <el-container>
    <el-header>
      <span>课程学习平台</span>
      <span></span>
    </el-header>
    <el-container>
      <el-aside class="sidebar">
        <el-button type="primary" class="create-course-btn">+ 创建课程</el-button>
        <el-menu default-active="1" class="sidebar-menu">
          <el-menu-item index="1" >
            <el-icon><Calendar /></el-icon>
            <span>我的课程</span>
          </el-menu-item>
          <el-menu-item index="2">
            <el-icon><star /></el-icon>
            <span>热门课程</span>
          </el-menu-item>
          <el-menu-item index="3">
            <el-icon><star /></el-icon>
            <span>热门老师</span>
          </el-menu-item>
          <el-menu-item index="4">
            <el-icon><user /></el-icon>
            <span>AI问答</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main class="mainbar">
        <span class="mainbar-title">我的课程</span>
        <div class="courses">
          <el-card v-for="(course, index) in courses" :key="index" class="course-card">
            <div class="course-header">
              <h3>{{ course.title }}</h3>
              <el-tag :type="getPublicTagType(course.publicStatus)">{{ course.publicStatus }}</el-tag>
            </div>
            <p>{{ course.university }} - {{ course.instructor }}</p>
            <div class="course-categories">
              <el-tag v-for="(cat, idx) in course.category" :key="idx" type="info">{{ cat }}</el-tag>
            </div>
            <div class="course-footer">
              <span class="rating">⭐ {{ course.rating }}</span>
              <span class="students">{{ course.students }}名学生已加入</span>
            </div>
          </el-card>
        </div>
      </el-main>
    </el-container>
    <el-main>
      <router-view/>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: 'App',
  components: {

  },
  data() {
    return {
      courses: [
        {
          title: 'Python编程入门',
          university: '南方科技大学',
          instructor: '张三',
          publicStatus: '公开课',
          category: ['计算机科学'],
          rating: 4.8,
          students: 1234,
        },
        {
          title: '数据结构与算法',
          university: '南方科技大学',
          instructor: '李四',
          publicStatus: '非公开课',
          category: ['计算机科学'],
          rating: 4.8,
          students: 987,
        },
        {
          title: '机器学习基础',
          university: '南方科技大学',
          instructor: '王五',
          publicStatus: '半公开课',
          category: ['人工智能', '计算机科学'],
          rating: 4.8,
          students: 2987,
        },
        {
          title: '人工智能',
          university: '南方科技大学',
          instructor: '王五',
          publicStatus: '半公开课',
          category: ['人工智能', '计算机科学'],
          rating: 4.8,
          students: 2987,
        },
      ],
    };
  },
  methods: {
    getPublicTagType(status) {
      switch (status) {
        case '公开课':
          return 'success';
        case '非公开课':
          return 'warning';
        case '半公开课':
          return 'info';
        default:
          return 'info';
      }
    },
  },
};
</script>

<style>
.sidebar {
  width: 250px;
  padding: 20px;
  background-color: #f0f0f0;
}

.create-course-btn {
  width: 80%;
  padding: 10px;
  color: white;
  border: none;
  border-radius: 30px;
  margin-bottom: 20px;
  cursor: pointer;
}

.sidebar-menu {
  padding: 20px;
  background-color: #f0f0f0;
}

.sidebar-menu-item {
  display: inline-flex;
  text-align: center;
  justify-content: center; /* 水平居中 */
  padding: 10px;
  cursor: pointer;
}

.mainbar {
  left: 10px;
  padding: 20px;
}

.mainbar-title{
  position: relative;  /* 使用绝对定位来使标题固定在左上角 */
  right: 610px;
  font-size: 36px;
  font-weight: bold;
  margin: 20px 0;      /* 添加一些间距使其更美观 */
}

.courses {
  display: flex;
  gap: 20px;
  flex-wrap: wrap;
  margin-left: 20px;
  margin-top: 50px;
}

.course-card {
  width: 300px;
  padding: 20px;
}

.course-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.course-footer {
  display: flex;
  justify-content: space-between;
  margin-top: 15px;
}

.rating {
  font-weight: bold;
}

.students {
  color: #999;
}
</style>