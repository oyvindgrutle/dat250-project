import { Button, Center, FormControl, FormLabel, Heading, Input, Link, Spinner, Text, VStack } from '@chakra-ui/react';
import React, { useState } from 'react';
import { SubmitHandler, useForm } from 'react-hook-form';
import { Redirect } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import Section from './Section';

interface Inputs {
    username: string;
    password: string;
}

const SignIn = (): JSX.Element => {
    const authContext = useAuth();
    const {
        register,
        handleSubmit,
        formState: { isSubmitting },
        reset,
    } = useForm<Inputs>();

    const [error, setError] = useState<string>('');

    const onSubmit: SubmitHandler<Inputs> = async (data) => {
        setError('');
        reset({ username: '', password: '' });
        authContext.login(data.username, data.password).catch((err: Error) => {
            switch (err.message) {
                case '401':
                    setError('Bad credentials :(');
                    break;
                case '500':
                    setError('Internal server error');
                    break;
                default:
                    setError('Something went wrong');
                    break;
            }
        });
    };

    return (
        <Center>
            <Section mt="10%" w="40%">
                {authContext.isAuthenticated ? (
                    <Redirect to="/profile" />
                ) : (
                    <>
                        <Heading mb="2rem" color="red.500">
                            Sign in
                        </Heading>
                        <form onSubmit={handleSubmit(onSubmit)}>
                            <VStack spacing="1rem">
                                <FormControl>
                                    <FormLabel>Brukernavn</FormLabel>
                                    <Input
                                        id="username"
                                        placeholder="username"
                                        {...register('username', {
                                            required: 'This is required',
                                        })}
                                    />
                                </FormControl>
                                <FormControl>
                                    <FormLabel>Password</FormLabel>
                                    <Input
                                        id="password"
                                        placeholder="password"
                                        type="password"
                                        {...register('password', {
                                            required: 'This is required',
                                        })}
                                    />
                                </FormControl>
                                {error && (
                                    <Center>
                                        <Text>{error}</Text>
                                    </Center>
                                )}
                                {authContext.inProgress && (
                                    <Center>
                                        <Spinner />
                                    </Center>
                                )}
                                <Center>
                                    <Button type="submit" colorScheme="red" isLoading={isSubmitting}>
                                        Sign in
                                    </Button>
                                </Center>
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
                    </>
                )}
            </Section>
        </Center>
    );
};

export default SignIn;
