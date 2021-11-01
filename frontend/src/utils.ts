export const getLocalStorageItem = (key: string): string | null => {
    const item = localStorage.getItem(key);
    return item ? item : null;
};

export const setLocalStorageItem = (key: string, value: string): void => {
    localStorage.setItem(key, value);
};
