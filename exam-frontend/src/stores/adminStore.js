import { defineStore } from 'pinia';
import httpInstance from '@/utils/http';

export const useAdminStore = defineStore('admin', {
    state: () => ({
        tokenName: '',
        tokenValue: '',
        loginId: null,
    }),

    actions: {
        async adminLogin(secretCode) {
            try {
                const res = await httpInstance.post('/admin/login', {
                    secretCode
                })

                if (res.code === 0) {
                    this.tokenName = res.data.tokenName
                    this.tokenValue = res.data.tokenValue
                    this.loginId = res.data.loginId
                    this.loginRole = 'ADMIN_ROLE'
                    return { success: true, message: 'Login successful' }
                } else {
                    return { success: false, message: res.message || 'Login failed' };
                }

            } catch (error) {
                return { success: false, message: 'An error occurred during login' };
            }
        }
    },
});
