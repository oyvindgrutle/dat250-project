import { createContext, useContext, useEffect, useState } from 'react';
import { authenticate, getUserDetails } from '../api/api';
import { AccountInfo, AuthContextState } from '../lib/types';
import { getLocalStorageItem, setLocalStorageItem } from '../utils';

interface Props {
    children: JSX.Element;
}

const initialState: AuthContextState = {
    isAuthenticated: false,
    account: null,
    login: async () => 200,
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
    const [token, setToken] = useState<string | null>(getLocalStorageItem('token'));
    const [isAuthenticated, setIsAuthenticated] = useState<boolean>(token ? true : false);
    const [account, setAccount] = useState<AccountInfo | null>(null);
    const [inProgress, setInProgress] = useState<boolean>(false);

    useEffect(() => {
        const getAccount = async (token: string) => {
            setInProgress(true);
            const response = await getUserDetails(token);
            const json = await response.json();
            setAccount(json);
            setInProgress(false);
        };
        if (token) getAccount(token);
    }, [token, isAuthenticated, setIsAuthenticated]);

    const login = async (username: string, password: string) => {
        setInProgress(true);
        const response = await authenticate(username, password, false);
        const json = await response.json();
        if (json.jwt) {
            setLocalStorageItem('token', json.jwt);
            setToken(json.jwt);
            setIsAuthenticated(true);
            setInProgress(false);
            return response.status;
        }
        setInProgress(false);
        throw Error(`${response.status}`);
    };

    const logout = () => {
        localStorage.removeItem('token');
        setToken(null);
        setIsAuthenticated(false);
        setAccount(null);
    };

    const contextValue: AuthContextState = {
        isAuthenticated,
        account,
        login,
        logout,
        inProgress,
    };

    return <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>;
};

export { useAuth, AuthProvider };
