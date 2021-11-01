import { Center, FormControl, Heading, Input, VStack, LinkBox, LinkOverlay, Button } from '@chakra-ui/react';
import React, { FormEvent, useState } from 'react';
import { useHistory } from 'react-router';
import Section from './Section';

const CodeBox = (): JSX.Element => {
    const [code, setCode] = useState<string>('');
    const history = useHistory();

    const handleSubmit = async (evt: FormEvent) => {
        evt.preventDefault();
        history.push(`/polls/${code}`);
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
                                <Button type="submit" onSubmit={handleSubmit}>
                                    Go!
                                </Button>
                            </Center>
                        </FormControl>
                    </form>
                </VStack>
            </Section>
        </Center>
    );
};

export default CodeBox;
