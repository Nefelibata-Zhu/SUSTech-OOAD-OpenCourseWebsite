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

        <el-row :gutter="20" v-for="(chapter, index) in this.chapters" :key="chapter.chapterID">
          <el-col :span="24" >
            <el-card :style="{backgroundColor: getChapterBackgroundColor(chapter.chapterType)}">
              <div style="display: flex; justify-content: space-between; align-items: center;">
                <div>
                  <h4 :style="[{'text-align': 'left'}, {color: getChapterTextColor(chapter.chapterType)}]">{{ chapter.chapterName }}</h4>
                  <p :style="[{'text-align': 'left'}, {color: getChapterTextColor(chapter.chapterType)}]">{{ chapter.chapterDescription }}</p>
                </div>
                <div class="open-chapter-button" :style="[{color: '#fff'}, {backgroundColor: getChapterTextColor(chapter.chapterType)}]" @click="openChapter">{{ getChapterStartText(chapter.chapterType) }}</div>
              </div>
              <div class="container">
                <el-icon size="20px" class="edit-and-delete-icons" @click="this.currentChapter={...chapter}; showAddChapterDialog=true"><edit /></el-icon>
                <el-icon size="20px" class="edit-and-delete-icons" @click="deleteChapter(index)"><delete /></el-icon>
              </div>
<!--              <el-button-group>-->
<!--                <el-button @click="this.currentChapter={...chapter}; showAddChapterDialog=true">-->
<!--                  <el-icon><edit /></el-icon>-->
<!--                </el-button>-->
<!--                <el-button @click="deleteChapter(index)">-->
<!--                  <el-icon><delete /></el-icon>-->
<!--                </el-button>-->
<!--              </el-button-group>-->
            </el-card>
          </el-col>
        </el-row>
        <div class="add-chapter-button" @click="this.currentChapter={...this.newChapter}; showAddChapterDialog=true">
          <el-icon><circle-plus /></el-icon>
          <span>添加新章节</span>
        </div>
        <el-dialog title="添加新章节" v-model="showAddChapterDialog">
          <el-form ref="newChapter" :model="currentChapter">
            <el-form-item label="章节名称" prop="chapterName">
              <el-input v-model="currentChapter.chapterName"></el-input>
            </el-form-item>
            <el-form-item label="章节描述" prop="chapterDescription">
              <el-input v-model="currentChapter.chapterDescription"></el-input>
            </el-form-item>
            <el-form-item label="章节类型" prop="chapterType">
              <el-select v-model="currentChapter.chapterType" placeholder="请选择章节类型">
                <el-option label="教学类" value="教学类"></el-option>
                <el-option label="作业类" value="作业类"></el-option>
                <el-option label="项目类" value="项目类"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <el-button @click="this.$refs.newChapter.resetFields(); showAddChapterDialog = false">取消</el-button>
          <el-button type="primary" @click="addNewChapter()">确定</el-button>
        </el-dialog>
      </el-main>
    </el-container>
  </div>
</template>


<script>
import {CirclePlus} from "@element-plus/icons-vue";

export default {
  name: 'CourseView',
  components: {
    CirclePlus

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
      ],
      showAddChapterDialog: false,
      currentChapter: null,
      newChapter: {
        chapterName: '',
        chapterDescription: '',
        chapterType: '',
      },
    };
  },
  methods: {
    openChapter(){

    },
    getChapterBackgroundColor(type) {
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
    getChapterTextColor(type) {
      switch (type) {
        case '教学类':
          return '#46895f';
        case '作业类':
          return '#3554b9';
        case '项目类':
          return '#a54726';
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
    addNewChapter(){
      if (this.currentChapter.chapterID) {
        // 如果有id，则编辑已存在章节
        console.log('update chapter');
        const index = this.chapters.findIndex(c => c.chapterID === this.currentChapter.chapterID);
        if (index !== -1) {
          this.chapters.splice(index, 1, {...this.currentChapter});
        }
      } else {
        // 没有id，添加新章节
        const newId = this.chapters.length + 1;  // 确保ID唯一
        const newChapter = {
          chapterID: newId,
          ...this.currentChapter,
        };
        this.chapters.push(newChapter);
      }
      this.showAddChapterDialog = false;
    },
    editChapter(){

    },
    deleteChapter(index){
      this.chapters.splice(index, 1);
    },
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

.open-chapter-button {
  height: 12px;
  text-align: center;
  border-radius: 25px;
  padding: 10px;
  cursor: pointer;
  margin-top: 15px; /* 顶部间距 */
  margin-right: 6px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 16px; /* 文字大小 */
  color: #666; /* 文字颜色 */
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

.add-chapter-button {
  height: 50px;
  border: 2px dashed #ccc; /* 虚线边框 */
  text-align: center;
  padding: 20px;
  cursor: pointer;
  margin-top: 20px; /* 顶部间距 */
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 16px; /* 文字大小 */
  color: #666; /* 文字颜色 */
}

.container {
  display: flex;
  flex-direction: row;
  justify-content: right;
  height: 100%;
}

.edit-and-delete-icons {
  cursor: pointer;
  margin-right: 6px;
  align-self: flex-end; /* 将图标组推向右下角 */
  padding: 10px; /* 右下角留点间隙 */
}
</style>