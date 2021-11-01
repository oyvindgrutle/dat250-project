export const URL = process.env.BACKEND_URL || 'http://localhost:8080';

export interface Poll {
    id: number;
    question: string;
    startTime: string;
    endTime: string;
    numYes: number;
    numNo: number;
}

export interface Answer {
    answer: boolean;
    poll: {
        id: string;
    };
}

export const fetchUsers = (): Promise<Response> => {
    return fetch(`${URL}/users`);
};

export const fetchPoll = (id: string): Promise<Response> => {
    return fetch(`${URL}/polls/${parseInt(id)}`);
};

export const postAnswer = (answer: Answer): Promise<Response> => {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(answer),
    };

    return fetch(`${URL}/answers`, requestOptions);
};
