<template>
  <div class='center-container'>
    <div class='paper-import-container'>
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
        <el-input v-model='bankTitle' placeholder='Bank Title' size='small' style='width: 200px' />
        <el-select v-model="subjectId" placeholder="Subject Name" size="small">
          <el-option v-for="item in subjectList" :key="item.subjectId" :label="item.subjectName"
              :value="item.subjectId" />
        </el-select>
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

const bankTitle = ref('')
const subjectId = ref(null)
const subjectList = ref([])
const uploadRef = ref(null)
const fileList = ref([])

const getAllSubjectInfo = async () => {
  const res = await httpInstance.get('/subject/list')
  console.log(res)
  if (res.code === 0) {
    subjectList.value = res.data
  } else {
    ElMessage.error(res.message)
  }
}

const cancelFunc = () => {
  bankTitle.value = ''
  if (uploadRef.value) {
    uploadRef.value.clearFiles()
  }
}

const submitFunc = async () => {

  console.log(fileList)
  if (!bankTitle.value) {
    ElMessage.error('Please enter a bank title.');
    return;
  }

  if (!subjectId.value) {
    ElMessage.error('Please select a subject.');
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
    formData.append('subjectId', subjectId.value);
    formData.append('bankTitle', bankTitle.value)

    const response = await httpInstance.post('/bank/upload', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });

    if (response.code === 0) {
      ElMessage.success('The file is uploaded successfully.');
      bankTitle.value = ''
      subjectId.value = null
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
  getAllSubjectInfo()
})
</script>

<style lang='scss' scoped>
.center-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 650px;

  .paper-import-container {
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
