export const URL = process.env.BACKEND_URL || 'http://localhost:8080';

export const fetchUsers = (): Promise<Response> => {
    return fetch(`${URL}/users`);
};

export const fetchPoll = (id: string): Promise<Response> => {
    return fetch(`${URL}/polls/${parseInt(id)}`);
};
