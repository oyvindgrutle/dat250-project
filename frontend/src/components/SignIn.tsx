import { Button, Center, Flex, FormControl, FormLabel, Heading, Input, Link, VStack } from '@chakra-ui/react';
import React from 'react';
import Section from './Section';

const SignIn = (): JSX.Element => {
    return (
        <Center>
            <Section mt="10%" w="40%">
                <Heading mb="2rem" color="red.500">
                    Sign in
                </Heading>
                <form>
                    <VStack spacing="1rem">
                        <FormControl>
                            <FormLabel>Email</FormLabel>
                            <Input required type="email" />
                        </FormControl>
                        <FormControl>
                            <FormLabel>Password</FormLabel>
                            <Input required type="password" />
                        </FormControl>
                        <FormControl>
                            <Center>
                                <Button type="submit" colorScheme="red">
                                    Sign in
                                </Button>
                            </Center>
                        </FormControl>
                    </VStack>
                </form>
                <Center my="1rem">
                    <p>or</p>
                </Center>
                <Center>
                    <Link color="red" href="/signup">
                        Sign up
                    </Link>
                </Center>
            </Section>
        </Center>
    );
};

export default SignIn;
