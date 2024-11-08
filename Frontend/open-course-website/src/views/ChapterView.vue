<template>
  <div>
    <el-container>
      <el-aside width="200px">
        <h2>章节列表</h2>
        <el-menu default-active="2" class="menu-style">
          <el-menu-item index="1">第一章</el-menu-item>
          <el-menu-item index="2">第二章</el-menu-item>
          <el-menu-item index="3">第三章</el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <el-card>
          <h2>第一章：Java基础语法</h2>
          <div class="slideway">
            <div @click="this.status='课件'" :class="{ active: status === '课件' }" style="cursor: pointer">
              <el-icon class="icon"><Document /></el-icon>
              <span>课件</span>
            </div>
            <div @click="this.status='视频'" :class="{ active: status === '视频' }" style="cursor: pointer">
              <el-icon class="icon"><VideoCamera /></el-icon>
              <span>视频</span>
            </div>
            <div @click="this.status='评论'" :class="{ active: status === '评论' }" style="cursor: pointer">
              <el-icon class="icon"><ChatLineSquare /></el-icon>
              <span>评论</span>
            </div>
            <div @click="this.status='附件'" :class="{ active: status === '附件' }" style="cursor: pointer">
              <el-icon class="icon"><Paperclip /></el-icon>
              <span>附件</span>
            </div>
          </div>
          <div>
            <div class="container">
              <h3>{{getText1(this.status)}}</h3>
              <el-button class="upload-button" type="primary">{{ getUploadButtonText(this.status) }}</el-button>
            </div>
            <div class="list">
              <el-row :gutter="20" v-for="(file, fileIndex) in this.coursewares" :key="file.fileID">
                <el-col :span="24" >
                  <el-card class="file-card">
                    <div class="file-info">
                      <span style="font-weight: bold; font-size: 20px; margin-bottom: 15px;">{{ file.fileName }}</span>
                      <span style="color: #999;">最后更新时间：{{ file.updateTime }}</span>
                    </div>
                    <div class="file-actions">
                      <el-icon class="download-button" @click="downloadFile(fileIndex)"><download /></el-icon>
                      <el-icon class="delete-button" @click="deleteFile(fileIndex)"><delete /></el-icon>
                    </div>
                  </el-card>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script>

import {Document, ChatLineSquare, Paperclip, VideoCamera} from "@element-plus/icons-vue";

export default {
  name: 'ChapterView',
  components: {
    Document,
    Paperclip,
    ChatLineSquare,
    VideoCamera

  },
  data() {
    return{
      chapterID: 1,
      status: '课件',
      coursewares: [
        {
          fileID: 1,
          fileName: 'Java基本数据类型.pdf',
          updateTime: '2024-10-03',
        },
        {
          fileID: 2,
          fileName: 'Java基本数据类型.pdf',
          updateTime: '2024-10-03',
        }
      ],
      videos: [

      ],
    }
  },
  methods: {
    getText1(status){
      if (status === '课件') {
        return '课件列表';
      } else if (status === '视频') {
        return '视频列表';
      } else if (status === '评论') {
        return '评论列表';
      } else if (status === '附件') {
        return '附件列表';
      }
    },
    getUploadButtonText(status){
      if (status === '课件') {
        return '上传课件';
      } else if (status === '视频') {
        return '上传视频';
      } else if (status === '评论') {
        return '发表评论';
      } else if (status === '附件') {
        return '上传附件';
      }
    },
    downloadFile(fileIndex){
      alert("Downloading the file" + fileIndex)
      console.log("Downloading the file" + fileIndex);
    },
    deleteFile(fileIndex){
      alert("Deleting the file" + fileIndex)
      console.log("Deleting the file" + fileIndex);
    }
  },

};
</script>

<style>
.slideway {
  padding: 5px;
  display: flex;
  height: 50px;
  font-size: 20px;
  align-items: center; /* Center vertically */
  justify-content: space-around;
  margin-top: 20px;
  background-color: #efefef;
}

.active {
  background-color: #ffffff;
  color: #03a9f4;
  border-radius: 10px;
  padding: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: background-color 0.5s ease, border-color 0.5s ease, box-shadow 0.5s ease;
}

.icon {
  margin-right: 10px;
}

.file-card {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.file-info {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  font-size: 16px;
}

.file-name {
  font-weight: bold;
}

.file-meta {
  color: #999;
}

.file-actions {
  position: absolute;
  top: 20px;
  right: 20px;
  color: #409EFF;
}

.container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.upload-button {
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
  color: #ffffff; /* 文字颜色 */
}

.download-button {
  margin-right: 10px;
  color: #16a34a;
}

.delete-button {
  margin-right: 10px;
  color: #cb8c08;
}
</style>