PGDMP     
    0                {            office    15.3    15.3                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16398    office    DATABASE     }   CREATE DATABASE office WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Indonesia.1252';
    DROP DATABASE office;
                postgres    false            �            1259    16729    tb_master_user    TABLE     q  CREATE TABLE public.tb_master_user (
    id integer NOT NULL,
    tgl_lahir timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    alamat character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    jenis_kelamin character varying(255) NOT NULL,
    nama character varying(255) NOT NULL,
    role_name character varying(255) NOT NULL
);
 "   DROP TABLE public.tb_master_user;
       public         heap    postgres    false            �            1259    16728    tb_master_user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tb_master_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.tb_master_user_id_seq;
       public          postgres    false    215                       0    0    tb_master_user_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.tb_master_user_id_seq OWNED BY public.tb_master_user.id;
          public          postgres    false    214            �            1259    16738    tb_master_user_role    TABLE     [   CREATE TABLE public.tb_master_user_role (
    role_name character varying(255) NOT NULL
);
 '   DROP TABLE public.tb_master_user_role;
       public         heap    postgres    false            i           2604    16732    tb_master_user id    DEFAULT     v   ALTER TABLE ONLY public.tb_master_user ALTER COLUMN id SET DEFAULT nextval('public.tb_master_user_id_seq'::regclass);
 @   ALTER TABLE public.tb_master_user ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    215    215            �          0    16729    tb_master_user 
   TABLE DATA                 public          postgres    false    215   �                  0    16738    tb_master_user_role 
   TABLE DATA                 public          postgres    false    216   
                  0    0    tb_master_user_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.tb_master_user_id_seq', 13, true);
          public          postgres    false    214            l           2606    16737 "   tb_master_user tb_master_user_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.tb_master_user
    ADD CONSTRAINT tb_master_user_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.tb_master_user DROP CONSTRAINT tb_master_user_pkey;
       public            postgres    false    215            n           2606    16742 ,   tb_master_user_role tb_master_user_role_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY public.tb_master_user_role
    ADD CONSTRAINT tb_master_user_role_pkey PRIMARY KEY (role_name);
 V   ALTER TABLE ONLY public.tb_master_user_role DROP CONSTRAINT tb_master_user_role_pkey;
       public            postgres    false    216            o           2606    16743 *   tb_master_user fk83q6usk7xbi0qo5xbytvdi7sj    FK CONSTRAINT     �   ALTER TABLE ONLY public.tb_master_user
    ADD CONSTRAINT fk83q6usk7xbi0qo5xbytvdi7sj FOREIGN KEY (role_name) REFERENCES public.tb_master_user_role(role_name) ON DELETE CASCADE;
 T   ALTER TABLE ONLY public.tb_master_user DROP CONSTRAINT fk83q6usk7xbi0qo5xbytvdi7sj;
       public          postgres    false    3182    216    215            �   V  x���Ao� 𻟂����A��l&�K�۵A}�8ښ�}��/�8N� 	����֛�떬��r����ݮ�t�-.�db���VMK���Ҏ������6]����)��EJ��bї8%�O�o�� %cθ�19c� , ]6R�q��s�.�%<ⷮ����&+mqOͱ�~�m�؎��ս<�"������D�e�u�!"Q��"B)��5h�r"�0"S�[�D�H��,�܃H#D�0"�5BFhPAC.� T��,�PI�Ad"��3 "�,�H��e���n����>A0���Ln���'�cPc�����Nݐۣ�"          `   x���v
Q���W((M��L�+I��M,.I-�/-E�9�
 2>/17US!��'�5XAC�$�J]Ӛ˓SRR�Rs�(7(95�b#J�Fpq ѝ\�     