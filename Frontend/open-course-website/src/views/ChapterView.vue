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
            <div>
              <el-icon class="icon"><Document /></el-icon>
              <span>课件</span>
            </div>
            <div>
              <el-icon class="icon"><VideoCamera /></el-icon>
              <span>视频</span>
            </div>
            <div>
              <el-icon class="icon"><ChatLineSquare /></el-icon>
              <span>评论</span>
            </div>
            <div>
              <el-icon class="icon"><Paperclip /></el-icon>
              <span>附件</span>
            </div>
          </div>
          <div>
            <h3>{{getText1(this.status)}}</h3>
            <div class="upload-button">
              <el-button type="primary" icon="el-icon-upload">{{ getUploadButtonText(this.status) }}</el-button>
            </div>
            <div class="content">
              <div class="file-item" v-for="file in files" :key="file.id">
                <div class="file-info">
                  <div class="file-name">{{  }}</div>
                  <div class="file-details">
                    <span>{{  }}</span>
                    <span>{{  }}</span>
                  </div>
                </div>
                <div class="file-actions">
                  <el-button type="text" icon="el-icon-download"></el-button>
                  <el-button type="text" icon="el-icon-edit"></el-button>
                  <el-button type="text" icon="el-icon-delete"></el-button>
                </div>
              </div>
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
      status: '课件',
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
    }
  },

};
</script>

<style>
.slideway {
  display: flex;
  height: 50px;
  font-size: 20px;
  align-items: center; /* Center vertically */
  justify-content: space-around;
  margin-top: 20px;
  background-color: #efefef ;
}
.icon {
  margin-right: 10px;
}
</style>