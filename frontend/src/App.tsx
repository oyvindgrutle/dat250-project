import React, { useEffect, useState } from 'react';
import { Button, ChakraProvider } from '@chakra-ui/react';

const fetchUsers = (): Promise<Response> => {
    return fetch('http://localhost:8080/users');
};

const App = (): JSX.Element => {
    const [users, setUsers] = useState();

    /*
    useEffect(() => {
        const fetchData = async () => {
            const response = await fetchUsers();
            const users = await response.json();
            setUsers(users);
        };
        fetchData();
    });
    */

    return <Button>click me</Button>;
};

export default App;
