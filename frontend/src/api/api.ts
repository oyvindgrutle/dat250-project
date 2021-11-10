import { Answer } from '../lib/types';

export const URL = process.env.BACKEND_URL || 'http://localhost:8080';

export const fetchUsers = (): Promise<Response> => {
    return fetch(`${URL}/users`);
};

export const fetchPoll = (id: string): Promise<Response> => {
    return fetch(`${URL}/polls/${parseInt(id)}`);
};

export const getUserDetails = (token: string): Promise<Response> => {
    const requestOptions = {
        method: 'GET',
        headers: {
            Authentication: `Bearer ${token}`,
        },
    };
    return fetch(`${URL}/user/user-by-token`, requestOptions);
};

export const postAnswer = (answer: Answer): Promise<Response> => {
    const jwt = localStorage.getItem('token');
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            Authentication: `Bearer ${jwt}`,
        },
        body: JSON.stringify(answer),
    };

    return fetch(`${URL}/answers`, requestOptions);
};

export const authenticate = (username: string, password: string, isRegistration: boolean) => {
    const requestOptions = {
        method: 'POST',
        headers: { 'Access-Control-Allow-Origin': '*', 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password }),
    };
    console.log(requestOptions);
    return fetch(`${URL}/authenticate/${isRegistration ? 'signup' : 'signin'}`, requestOptions);
};
