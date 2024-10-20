<template>
  <div>
    <el-container>
      <el-aside width="200px">
        <el-menu default-active="2" class="menu-style">
          <el-menu-item index="1">主页</el-menu-item>
          <el-menu-item index="2">章节</el-menu-item>
          <el-menu-item index="3">问题</el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <h2 style="margin-bottom: 20px;">课程章节</h2>
        <el-row :gutter="20" v-for="chapter in this.chapters" :key="chapter.chapterID">
          <el-col :span="24" >
            <el-card>
              <div style="display: flex; justify-content: space-between; align-items: center;">
                <div>
                  <h4>{{ chapter.chapterName }}</h4>
                  <p>{{ chapter.chapterDescription }}</p>
                </div>
                <el-button-group>
                  <el-button>
                    <el-icon><edit /></el-icon>
                  </el-button>
                  <el-button>
                    <el-icon><delete /></el-icon>
                  </el-button>
                </el-button-group>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>


<script>
export default {
  name: 'CourseView',
  components: {

  },
  data() {
    return {
      courseID: 1,
      courseName: '计算机程序设计基础',
      courseType: '公开课',
      chapters: [
        {
          chapterID: 1,
          chapterName: 'Java基础语法',
          chapterDescription: '变量、数据类型',
          chapterType: '教学类',
        },
        {
          chapterID: 2,
          chapterName: 'Java基础练习',
          chapterDescription: '20道编程练习题',
          chapterType: '作业类',
        },
        {
          chapterID: 3,
          chapterName: '象棋小游戏项目',
          chapterDescription: '用Java设计并实现一个象棋小游戏',
          chapterType: '项目类',
        }
      ]
    };
  },
  methods: {
    getChapterColor(type) {
      switch (type) {
        case '教学类':
          return 'success';
        case '作业类':
          return 'warning';
        case '项目类':
          return 'info';
        default:
          return 'primary';
      }
    }
  },
};
</script>

<style scoped>
.menu-style {
  height: 100%;
}

.el-row {
  margin-bottom: 20px;
}

</style>