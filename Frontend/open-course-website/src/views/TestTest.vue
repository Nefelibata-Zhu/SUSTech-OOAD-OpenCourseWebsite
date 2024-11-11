<!--<script>-->

<!--</script>-->

<!--<template>-->
<!--  <el-card class="comment-card">-->
<!--    <div class="comment-header">-->
<!--      <el-avatar class="comment-avatar" size="large" src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>-->
<!--      <div class="comment-info">-->
<!--        <div class="comment-name">COMMENTOR</div>-->
<!--        <div class="comment-date">20 April 2022, at 14:88 PM</div>-->
<!--      </div>-->
<!--    </div>-->
<!--    <div class="comment-body">-->
<!--      Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime quisquam vero adipisci beatae voluptas dolor ame.-->
<!--    </div>-->
<!--  </el-card>-->
<!--</template>-->

<!--<style scoped>-->
<!--.comment-card {-->
<!--  width: 100%; /* Adjust based on your layout */-->
<!--  border-radius: 8px;-->
<!--  box-shadow: none;-->
<!--  border: 1px solid #ccc;-->
<!--}-->

<!--.comment-header {-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--  margin-bottom: 12px; /* Spacing between header and body */-->
<!--}-->

<!--.comment-avatar {-->
<!--  margin-right: 12px;-->
<!--}-->

<!--.comment-info {-->
<!--  flex-grow: 1;-->
<!--}-->

<!--.comment-name {-->
<!--  font-size: 16px;-->
<!--  font-weight: bold;-->
<!--  margin-bottom: 4px; /* Small space between name and date */-->
<!--}-->

<!--.comment-date {-->
<!--  font-size: 14px;-->
<!--  color: #999;-->
<!--}-->

<!--.comment-body {-->
<!--  font-size: 14px;-->
<!--  color: #666;-->
<!--}-->
<!--</style>-->



<template>
  <el-card class="comment-card">
    <el-row class="comment-header">
      <el-avatar class="comment-avatar" shape="square" size="large" src="path_to_avatar.png"/>
      <div class="comment-info">
        <div class="comment-name">COMMENTOR</div>
        <div class="comment-date">20 April 2022, at 14:88 PM</div>
      </div>
    </el-row>
    <el-row class="comment-content">
      Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime quisquam vero adipisci beatae voluptas dolor ame.
    </el-row>
  </el-card>
</template>

<script>
import { ElRow, ElAvatar } from 'element-plus';

export default {
  components: {
    ElRow,
    ElAvatar
  }
}
</script>

<style scoped>
.comment-card {
  border-radius: 10px;
  padding: 12px;
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

