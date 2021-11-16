import { Button, Center, FormControl, Heading, Input, VStack, Text } from '@chakra-ui/react';
import React, { FormEvent, useState } from 'react';
import { useHistory } from 'react-router';
import { useAuth } from '../context/AuthContext';
import Section from './Section';

const CodeBox = (): JSX.Element => {
    const [code, setCode] = useState<string>('');
    const history = useHistory();

    const authContext = useAuth();

    const handleSubmit = async (evt: FormEvent) => {
        evt.preventDefault();
        history.push(`/polls/${code}`);
    };

    const handleCreate = () => {
        if (authContext.account) history.push('/create');
        else history.push('/signin');
    };

    return (
        <Center>
            <Section mt="10%">
                <VStack spacing="1rem" p="2rem" minW="30%" maxW="600px">
                    <Heading color="red.600" as="h1">
                        ENTER POLL ID
                    </Heading>
                    <form onSubmit={handleSubmit}>
                        <FormControl isRequired>
                            <Input type="text" value={code} onChange={(evt) => setCode(evt.target.value)} />
                        </FormControl>
                        <FormControl mt="1rem">
                            <Center>
                                <Button type="submit" colorScheme="red" onSubmit={handleSubmit}>
                                    Go!
                                </Button>
                            </Center>
                        </FormControl>
                    </form>
                    <Text>or</Text>
                    <Button onClick={handleCreate}>Create Poll</Button>
                </VStack>
            </Section>
        </Center>
    );
};

export default CodeBox;
