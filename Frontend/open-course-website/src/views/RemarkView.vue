<template>
  <el-container>
    <el-aside class="sidebar">
      <el-menu default-active="1" class="sidebar-menu">
        <el-menu-item index="1">
          <el-icon><Calendar /></el-icon>
          <span>待评价课程</span>
        </el-menu-item>
        <el-menu-item index="2">
          <el-icon><Star /></el-icon>
          <span>已评价课程</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-main class="mainbar">
      <div class="evaluation-wrapper">
        <h1>{{ courseName }}</h1>
        <h2>课程评价</h2>
        <div class="evaluation-section">
          <!-- 总体评分 -->
          <div class="overall-rating">
            <h3>总体评分</h3>
            <el-rate v-model="overallRating" void-color="#C0C4CC" :colors="['#F7BA2A']" size="60"></el-rate>
          </div>

          <!-- 具体指标评分 -->
          <div class="criteria-ratings">
            <h3>具体指标评分</h3>
            <div class="criteria">
              <span>教学质量</span>
              <el-slider v-model="ratings.teachingQuality" :min="1" :max="5" :step="1"></el-slider>
            </div>
            <div class="criteria">
              <span>课程内容</span>
              <el-slider v-model="ratings.courseContent" :min="1" :max="5" :step="1"></el-slider>
            </div>
            <div class="criteria">
              <span>作业难度</span>
              <el-slider v-model="ratings.assignmentDifficulty" :min="1" :max="5" :step="1"></el-slider>
            </div>
          </div>

          <!-- 文本评价 -->
          <div class="text-review">
            <h3>文字评价</h3>
            <el-input
              type="textarea"
              v-model="textReview"
              placeholder="请输入您的评价"
              rows="5"
            ></el-input>
          </div>

          <!-- 提交按钮 -->
          <el-button type="primary" @click="submitReview">提交评价</el-button>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      courseName: 'Python编程基础',
      overallRating: 0,
      ratings: {
        teachingQuality: 3,
        courseContent: 3,
        assignmentDifficulty: 3,
      },
      textReview: '',
    };
  },
  methods: {
    submitReview() {
      console.log('总体评分:', this.overallRating);
      console.log('教学质量:', this.ratings.teachingQuality);
      console.log('课程内容:', this.ratings.courseContent);
      console.log('作业难度:', this.ratings.assignmentDifficulty);
      console.log('文字评价:', this.textReview);
      // 这里可以添加提交数据的逻辑，例如调用 API
      this.$message.success('评价已提交！');
    },
  },
};
</script>

<style scoped>
.mainbar {
  padding: 20px;
}

.evaluation-wrapper {
  background-color: #f8fdff;
  border-radius: 10px; /* 添加圆角 */
  padding: 20px;
  box-shadow: 0 4px 6px rgba(153, 170, 199, 0.1); /* 添加阴影 */
}

.evaluation-section {
  max-width: 600px;
  margin: 0 auto;
}

.criteria {
  margin-bottom: 20px;
}

.text-review {
  margin-top: 20px;
  margin-bottom: 20px; /* 为文本框和按钮之间添加间距 */
}

h1,
h2,
h3 {
  text-align: left; /* 让标题左对齐 */
  color: #333; /* 设置标题颜色 */
}

h3 {
  color: #888; /* 修改所有的标题颜色为灰色 */
}

.criteria span,
.text-review h3 {
  color: #888; /* 使指标标题颜色变灰 */
  display: block; /* 让标题占一行 */
  text-align: left; /* 标题左对齐 */
}

.el-rate {
  margin-top: 10px;
}

.el-slider {
  margin-top: 10px;
  width: 100%; /* 确保滑块充满容器 */
}
</style>
