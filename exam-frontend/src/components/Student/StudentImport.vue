<template>
  <div class='center-container'>
    <div class='student-import-container'>
      <el-upload
          ref='uploadRef'
          :auto-upload='false'
          :file-list='fileList'
          :multiple='true'
          action='#'
          class="upload-demo"
          drag
          @change='handleFileChange'
      >
        <el-icon class="el-icon--upload">
          <upload-filled />
        </el-icon>
        <div class="el-upload__text">
          Drop file here or <em>click to upload</em>
        </div>
        <template #tip>
          <div class="el-upload__tip">
            Only csv files are supported for user data.
          </div>
        </template>
      </el-upload>
      <div class='select-classNo'>
        <el-input v-model='classNo' placeholder='Enter a class number.' size='small' style='width: 200px'/>
        <div>
          <el-button round @click='cancelFunc'>Cancel</el-button>
          <el-button round type='primary' @click='submitFunc'>Submit</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {UploadFilled} from '@element-plus/icons-vue'
import {onMounted, ref} from 'vue'
import httpInstance from '@/utils/http.js'
import {ElMessage} from 'element-plus'

const classNo = ref('')
const classList = ref([])
const uploadRef = ref(null)
const fileList = ref([])

const getAllClassInfo = async () => {
  const res = await httpInstance.get('/clazz/list')
  if (res.code === 0) {
    classList.value = res.data
  } else {
    ElMessage.error(res.message)
  }
}

const cancelFunc = () => {
  classNo.value = ''
  if (uploadRef.value) {
    uploadRef.value.clearFiles()
  }
}

const submitFunc = async () => {

  console.log(fileList)
  if (!classNo.value) {
    ElMessage.error('Please select a class number.');
    return;
  }
  console.log(uploadRef.value)
  const files = fileList.value;
  if (!files.length) {
    ElMessage.error('Please upload the file.');
    return;
  }

  try {

    const formData = new FormData();
    formData.append('file', files[0].raw);
    formData.append('classNo', classNo.value);

    const response = await httpInstance.post('/user/upload', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });

    if (response.code === 0) {
      ElMessage.success('The file is uploaded successfully.');
      classNo.value = ''
      if (uploadRef.value) {
        uploadRef.value.clearFiles()
      }

    } else {
      ElMessage.error(response.message || 'File uploading failed.');
    }
  } catch (error) {
    console.error('Error uploading file:', error);
    ElMessage.error('An error occurred while uploading the file.');
  }
};

const handleFileChange = (file, list) => {
  console.log('Document change:', list);
  fileList.value = list
}

onMounted(() => {
  getAllClassInfo()
})
</script>

<style lang='scss' scoped>
.center-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 650px;

  .student-import-container {
    ::v-deep .el-upload-dragger {
      width: 600px;
      height: 300px;
      background-color: #24292f;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .select-classNo {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
  }
}
</style>
