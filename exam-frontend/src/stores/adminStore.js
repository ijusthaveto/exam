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
                    localStorage.setItem('tokenName', res.data.tokenName)
                    localStorage.setItem('tokenValue', res.data.tokenValue)
                    localStorage.setItem('loginId', res.data.loginId)
                    localStorage.setItem('loginRole', 'ADMIN_ROLE')
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
