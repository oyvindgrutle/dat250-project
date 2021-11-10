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

export interface AccountInfo {
    name: string;
    username: string;
    polls: number[];
}

export interface AuthContextState {
    isAuthenticated: boolean;
    account: AccountInfo | null;
    login: (username: string, password: string) => Promise<void>;
    logout: () => void;
    inProgress: boolean;
}
