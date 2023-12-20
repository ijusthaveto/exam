import { defineStore } from 'pinia';
import httpInstance from '@/utils/http.js';

export const useExamStore = defineStore('exam', {
  state: () => ({
    examList: [],
    examId: null,
    questionList: [],
    singleAnswer: [],
    mutipleAnswer: [],
    judgeAnswer: []
  }),

  actions: {
    async selectExamListById() {
      try {
        const loginId = localStorage.getItem('loginId');
        const res = await httpInstance.get(`/exam/list/${loginId}`);
        this.examList = res.data;
      } catch (error) {
        console.error('Error fetching exam list:', error);
      }
    },

    async fetchQuestionsByExamId(examId) {
      try {
        const res = await httpInstance.get(`/exam/${examId}`);
        this.setQuestionList(res.data);
        return { success: true, message: 'Questions fetched successfully' };
      } catch (error) {
        console.error('Error fetching questions:', error);
        return { success: false, message: 'Error fetching questions' };
      }
    },

    async getQuestionListByExamId() {
      try {
        const res = await httpInstance.get(`/exam/${this.examId}`);
        this.setQuestionList(res.data);
      } catch (error) {
        console.error('Error fetching questions:', error);
      }
    },
  },

  getters: {
    getExamList() {
      return this.examList;
    },

    getExamId() {
      return this.examId;
    },
  },

  mutations: {
    setExamId(id) {
      this.examId = id;
    },

    setQuestionList(questionList) {
      this.questionList = questionList;
    },
  },
});
