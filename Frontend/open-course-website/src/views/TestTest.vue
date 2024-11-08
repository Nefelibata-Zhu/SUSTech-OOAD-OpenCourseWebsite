<template>
  <div class="custom-switch">
    <button class="icon-button">
      <i class="el-icon-search"></i>搜索
    </button>
    <label class="switch">
      <input type="checkbox" v-model="isChecked">
      <span class="slider round"></span>
    </label>
    <div class="labels">
      <span>允许</span>
      <span>拒绝</span>
    </div>
    <button class="icon-button">
      <i class="el-icon-paperclip"></i>附件
    </button>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const isChecked = ref(false);

    return { isChecked };
  }
}
</script>

<style scoped>
.custom-switch {
  display: flex;
  align-items: center;
  gap: 12px;
  background-color: #f5f7fa;
  padding: 8px 16px;
  border-radius: 5px;
}

.icon-button {
  background: #ffffff;
  border: 1px solid #dcdfe6;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
  display: flex;
  align-items: center;
  font-size: 14px;
}

.icon-button i {
  font-size: 16px;
  margin-right: 5px;
}

.switch {
  position: relative;
  display: inline-block;
  width: 34px;
  height: 20px;
}

.switch input {
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  transition: .4s;
  border-radius: 34px;
}

.slider:before {
  position: absolute;
  content: "";
  height: 12px;
  width: 12px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  transition: .4s;
  border-radius: 50%;
}

input:checked + .slider {
  background-color: #409EFF;
}

input:checked + .slider:before {
  transform: translateX(14px);
}

.labels {
  display: flex;
  gap: 10px;
  font-size: 14px;
}
</style>
