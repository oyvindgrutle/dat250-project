import { createContext, useContext } from 'react';
import { getUserDetails } from '../api/api';
import { AccountInfo, AuthContextState } from '../lib/types';
import { getLocalStorageItem } from '../utils';

interface Props {
    children: JSX.Element;
}

const initialState: AuthContextState = {
    isAuthenticated: false,
    account: null,
    login: async () => undefined,
    logout: () => undefined,
    inProgress: false,
};

export const AuthContext = createContext<AuthContextState>(initialState);

const useAuth = () => {
    const ctx = useContext(AuthContext);
    if (ctx === undefined) throw new Error('useAuth must be used within an AuthProvider');
    return ctx;
};

const AuthProvider = ({ children }: Props) => {
    const isAuthenticated = getLocalStorageItem('token') ? true : false;

    const getAccount = async (token: string | null) => {
        if (!token) return;
        const response = await getUserDetails(token);
        const json = await response.json();
        return json;
    };

    const account = isAuthenticated ? getAccount(getLocalStorageItem('token')) : null;

    const login = async () => {};
};
