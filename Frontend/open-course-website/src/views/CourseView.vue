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

        <el-row :gutter="20" v-for="(chapter, chapterIndex) in this.course.chapters" :key="chapter.chapterID">
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
                <el-icon size="20px" class="edit-and-delete-icons" @click="deleteChapter(chapterIndex)"><delete /></el-icon>
              </div>
<!--              <el-button-group>-->
<!--                <el-button @click="this.currentChapter={...chapter}; showAddChapterDialog=true">-->
<!--                  <el-icon><edit /></el-icon>-->
<!--                </el-button>-->
<!--                <el-button @click="deleteChapter(chapterIndex)">-->
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
import { CirclePlus } from "@element-plus/icons-vue";
import { useRoute } from 'vue-router';
import { ref, reactive, onMounted } from 'vue';
import axios from 'axios';

export default {
  name: 'CourseView',
  components: {
    CirclePlus
  },
  setup() {
    const route = useRoute();
    console.log("读取路径参数中的课程ID:", route.params.courseID);
    const courseID = ref(route.params.courseID);

    console.log('courseID:', courseID.value);
    const course = reactive({
      courseID: 0,
      courseName: '',
      courseType: '',
      chapters: []
    });
    const showAddChapterDialog = ref(false);
    const currentChapter = ref(null);
    const newChapter = reactive({
      chapterName: '',
      chapterDescription: '',
      chapterType: '',
    });

    const loadCourseData = () => {
      const url = `http://127.0.0.1:4523/m1/5467700-5143103-default/api/course/getCourseData/${courseID.value}`;
      axios.get(url)
          .then(response => {
            const { courseID, courseName, courseType, chapters } = response.data;
            course.courseID = courseID;
            course.courseName = courseName;
            course.courseType = courseType;
            course.chapters = chapters.map(ch => ({
              chapterID: ch.chapterID,
              chapterName: ch.chapterName,
              chapterDescription: ch.chapterDescription,
              chapterType: ch.chapterType
            }));
          })
          .catch(error => {
            console.error("获取课程数据时发生错误:", error);
          });
    };


    const getChapterBackgroundColor = (type) => {
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
    };

    const getChapterTextColor = (type) => {
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
    };

    const getChapterStartText = (type) => {
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
    };

    const addNewChapter = () => {
      if (currentChapter.value.chapterID) {
        const index = course.chapters.findIndex(c => c.chapterID === currentChapter.value.chapterID);
        if (index !== -1) {
          course.chapters.splice(index, 1, { ...currentChapter.value });
        }
      } else {
        const newId = course.chapters.length + 1;
        const newChapterData = {
          chapterID: newId,
          ...currentChapter.value,
        };
        course.chapters.push(newChapterData);
      }
      showAddChapterDialog.value = false;
    };

    const deleteChapter = (index) => {
      course.chapters.splice(index, 1);
    };

    onMounted(() => {
      loadCourseData();
    });

    return {
      courseID,
      course,
      showAddChapterDialog,
      currentChapter,
      newChapter,
      loadCourseData,
      getChapterBackgroundColor,
      getChapterTextColor,
      getChapterStartText,
      addNewChapter,
      deleteChapter
    };
  }
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