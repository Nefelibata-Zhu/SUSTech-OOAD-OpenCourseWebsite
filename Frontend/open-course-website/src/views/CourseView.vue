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
        <div style="display: flex">
          <div class="chapterTypeBox">教学类</div>
          <div class="chapterTypeBox">作业类</div>
          <div class="chapterTypeBox">项目类</div>
        </div>

        <el-row :gutter="20" v-for="chapter in this.chapters" :key="chapter.chapterID">
          <el-col :span="24" >
            <el-card :style="{backgroundColor: getChapterColor(chapter.chapterType)}">
              <div style="display: flex; justify-content: space-between; align-items: center;">
                <div>
                  <h4>{{ chapter.chapterName }}</h4>
                  <p>{{ chapter.chapterDescription }}</p>
                </div>
                <el-button @click="openChapter">{{ getChapterStartText(chapter.chapterType) }}</el-button>
              </div>
              <el-button-group>
                <el-button>
                  <el-icon><edit /></el-icon>
                </el-button>
                <el-button>
                  <el-icon><delete /></el-icon>
                </el-button>
              </el-button-group>
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
    openChapter(){

    },
    getChapterColor(type) {
      switch (type) {
        case '教学类':
          return '#dcfce7';
        case '作业类':
          return '#dbeafe';
        case '项目类':
          return '#ffedd5';
        default:
          return 'primary';
      }
    },
    getChapterStartText(type){
      switch (type) {
        case '教学类':
          return '开始学习';
        case '作业类':
          return '开始作业';
        case '项目类':
          return '开始项目';
        default:
          return '开始';
      }
    },
    getChapterStartButtonColor(type){
      switch (type) {
        case '教学类':
          return '开始学习';
        case '作业类':
          return '开始作业';
        case '项目类':
          return '开始项目';
        default:
          return '开始';
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

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
}

.chapterTypeBox {
  width: 80px;
  height: 40px;
  margin-top: 20px;
  margin-bottom: 20px;
  margin-right: 20px;
  border-radius: 25px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 16px;
  font-weight: normal;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
}

.chapterTypeBox:nth-child(1){
  background-color: #dcfce7;
  color: #46895f;
}

.chapterTypeBox:nth-child(2){
  background-color: #dbeafe;
  color: #3554b9;
}

.chapterTypeBox:nth-child(3){
  background-color: #ffedd5;
  color: #a54726;
}

.chapter-move {

}
</style>