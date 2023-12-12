import { defineStore } from "pinia";

export const useAdminStore = defineStore('admin', {
    state: () => ({
        tokenName: undefined,
        tokenValue: undefined,
        loginId: undefined
    })

    
})