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
          <div class="list">
            <template v-if="this.status === '课件'">
              <div class="container">
                <h3>{{getText1(this.status)}}</h3>
                <el-button class="upload-button" type="primary">{{ getUploadButtonText(this.status) }}</el-button>
              </div>
              <el-row :gutter="20" v-for="(file, fileIndex) in this.coursewares" :key="file.fileID" style="margin-bottom: 25px">
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
            </template>

            <template v-if="this.status === '视频'">
              <div class="container">
                <h3>{{getText1(this.status)}}</h3>
                <el-button class="upload-button" type="primary">{{ getUploadButtonText(this.status) }}</el-button>
              </div>
              <el-row :gutter="20" v-for="(file, fileIndex) in this.videos" :key="file.fileID" style="margin-bottom: 25px">
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
            </template>

            <template v-if="this.status === '附件'">
              <div class="container">
                <h3>{{getText1(this.status)}}</h3>
                <el-button class="upload-button" type="primary">{{ getUploadButtonText(this.status) }}</el-button>
              </div>
              <el-row :gutter="20" v-for="(file, fileIndex) in this.attachments" :key="file.fileID" style="margin-bottom: 25px">
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
            </template>

            <template v-if="this.status === '评论'">
              <div class="container">
                <h3>{{getText1(this.status)}}</h3>
                <el-button class="upload-button" type="primary" @click="dialogVisible=true">{{ getUploadButtonText(this.status) }}</el-button>
              </div>
              <el-row :gutter="20" v-for="(comment) in this.comments" :key="comment.commentID" style="margin-bottom: 25px">
                <el-col :span="24" >
                  <el-card class="comment-card">
                    <el-row class="comment-header">
                      <el-avatar class="comment-avatar" shape="square" size="large" />
                      <div class="comment-info">
                        <div class="comment-name">{{ comment.user.userName }}</div>
                        <div class="comment-date">{{ comment.commentTime }}</div>
                      </div>
                    </el-row>
                    <el-row class="comment-content">{{ comment.content }}</el-row>
                  </el-card>
                </el-col>
              </el-row>
            </template>
          </div>
        </el-card>

        <el-dialog
            title="评论"
            width="30%"
            v-model="dialogVisible">
          <el-input
              type="textarea"
              v-model="commentText"
              rows=5
              placeholder="请输入内容..."
              style="margin-bottom: 20px;">
          </el-input>
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitComment">发表</el-button>
        </el-dialog>

      </el-main>
    </el-container>
  </div>
</template>

<script>
import { ref, reactive } from 'vue';
import { Document, ChatLineSquare, Paperclip, VideoCamera } from '@element-plus/icons-vue';
import axios from 'axios';

export default {
  name: 'ChapterView',
  components: {
    Document,
    Paperclip,
    ChatLineSquare,
    VideoCamera,
  },
  setup() {
    // Reactive state
    const chapterID = ref(1);  // 当前章节ID

    const user = ref({
      userID: 1,
      userName: '张三',
    });

    // 课件、视频、附件、评论
    const coursewares = reactive([]);
    const videos = reactive([]);
    const attachments = reactive([]);
    const comments = reactive([]);

    const status = ref('课件');
    const dialogVisible = ref(false);
    const commentText = ref('');

    // Methods to get data from the backend
    const loadCoursewares = async () => {
      try {
        const url = `http://127.0.0.1:4523/m1/5467700-5143103-default/api/chapter/getCoursewares/${chapterID.value}`;
        const response = await axios.get(url);
        console.log("课件列表:", response.data);
        const newCoursewares = response.data.map(item => ({
          fileID: item.fileID ?? null,
          fileType: item.fileType ?? '',
          fileUsage: item.fileUsage ?? 'courseware', // 如果不存在则设置默认值
          fileName: item.fileName ?? '',
          updateTime: item.updateTime ?? ''
        }));

        coursewares.splice(0, coursewares.length, ...newCoursewares);
        console.log("课件列表:", coursewares);
      } catch (error) {
        console.error('Failed to load coursewares:', error);
      }
    };

    const loadVideos = async () => {
      try {
        const url = `http://127.0.0.1:4523/m1/5467700-5143103-default/api/chapter/getVideos/${chapterID.value}`;
        const response = await axios.get(url);

        const newVideos = response.data.map(item => ({
          fileID: item.fileID ?? null,
          fileType: item.fileType ?? '',
          fileUsage: item.fileUsage ?? 'video', // 如果不存在则设置默认值
          fileName: item.fileName ?? '',
          updateTime: item.updateTime ?? ''
        }));

        videos.splice(0, videos.length, ...newVideos);
        console.log("视频列表:", videos);
      } catch (error) {
        console.error('Failed to load videos:', error);
      }
    };

    const loadComments = async () => {
      try {
        const url = `http://127.0.0.1:4523/m1/5467700-5143103-default/api/chapter/getComments/${chapterID.value}`;
        const response = await axios.get(url);

        const newComments = response.data.map(item => ({
          commentID: item.commentID ?? null,
          content: item.content ?? '',
          commentTime: item.commentTime ?? '',
          user: item.user ?? null,
        }));

        comments.splice(0, comments.length, ...newComments);
        console.log("评论列表:", comments);
      } catch (error) {
        console.error('Failed to load comments:', error);
      }
    };

    const loadAttachments = async () => {
      try {
        const url = `http://127.0.0.1:4523/m1/5467700-5143103-default/api/chapter/getAttachments/${chapterID.value}`;
        const response = await axios.get(url);

        const newAttachments = response.data.map(item => ({
          fileID: item.fileID ?? null,
          fileType: item.fileType ?? '',
          fileUsage: item.fileUsage ?? 'attachment', // 如果不存在则设置默认值
          fileName: item.fileName ?? '',
          updateTime: item.updateTime ?? ''
        }));

        attachments.splice(0, attachments.length, ...newAttachments);
        console.log("附件列表:", attachments);
      } catch (error) {
        console.error('Failed to load attachments:', error);
      }
    };

    // Call the load methods when component is created or chapterID changes
    loadCoursewares();
    loadVideos();
    loadComments();
    loadAttachments();

    // Methods
    const getText1 = (statusValue) => {
      if (statusValue === '课件') {
        return '课件列表';
      } else if (statusValue === '视频') {
        return '视频列表';
      } else if (statusValue === '评论') {
        return '评论列表';
      } else if (statusValue === '附件') {
        return '附件列表';
      }
    };

    const getUploadButtonText = (statusValue) => {
      if (statusValue === '课件') {
        return '上传课件';
      } else if (statusValue === '视频') {
        return '上传视频';
      } else if (statusValue === '评论') {
        return '发表评论';
      } else if (statusValue === '附件') {
        return '上传附件';
      }
    };

    const downloadFile = (fileIndex) => {
      alert(`Downloading the file ${fileIndex}`);
      console.log(`Downloading the file ${fileIndex}`);
    };

    const deleteFile = (fileIndex) => {
      alert(`Deleting the file ${fileIndex}`);
      console.log(`Deleting the file ${fileIndex}`);
    };

    const submitComment = () => {
      if (!commentText.value.trim()) {
        alert('评论内容不能为空！');
        return;
      }

      const newComment = {
        commentID: comments.length + 1,
        content: commentText.value,
        commentTime: new Date().toLocaleString('zh-CN', { hour12: false }).slice(0, 16).replaceAll('/', '-'), // 2024-10-03 12:00
        user: user.value,
      };

      comments.push(newComment);
      commentText.value = '';
      dialogVisible.value = false;
    };

    return {
      chapterID,
      user,
      coursewares,
      videos,
      comments,
      attachments,
      status,
      dialogVisible,
      commentText,
      getText1,
      getUploadButtonText,
      downloadFile,
      deleteFile,
      submitComment,
      loadCoursewares,
      loadVideos,
      loadComments,
      loadAttachments
    };
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
  border-radius: 10px;
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

.comment-card {
  border-radius: 10px;
  background-color: #fff;
  font-size: 18px;
}

.comment-header {
  align-items: center;
}

.comment-avatar {
  margin-right: 20px;
}

.comment-info {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.comment-name {
  font-weight: bold;
  margin-bottom: 10px;
}

.comment-date {
  font-weight: bold;
  font-size: 0.80em;
  color: #a2a9b4;
}

.comment-content {
  font-weight: bold;
  margin-top: 20px;
  color: #7f8692;
  text-align: left;
  line-height: 1.5;
}
</style>