import { defineStore } from "pinia";

export const useLoginStore = defineStore('login', {

    state: () => ({
        loginId: undefined,
        tokenName: undefined,
        tokenValue: undefined
    }),

    actions: {
    }
})